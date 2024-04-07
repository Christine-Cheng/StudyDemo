#!/bin/bash
# mysql 主从备份监控slave状态nagios脚本
cmd='mysql -uroot -p123456 -P3306 -h192.168.1.38 -Be'
status=`$cmd 'show slave status\G'`
Seconds_Behind_Master=`echo "$status"|awk '/Seconds_Behind_Master/{FS=":";print $2}'`
Read_Master_Log_Pos=`echo "$status"|awk '/Read_Master_Log_Pos/{FS=":";print $2}'`
Exec_Master_Log_Pos=`echo "$status"|awk '/Exec_Master_Log_Pos/{FS=":";print $2}'`
Last_IO_Errno=`echo "$status"|awk '/Last_IO_Errno/{FS=":";print $2}'`
Last_SQL_Errno=`echo "$status"|awk '/Last_SQL_Errno/{FS=":";print $2}'`
Slave_IO_Running=`echo "$status"|awk '/Slave_IO_Running/{FS=":";print $2}'`
Slave_SQL_Running=`echo "$status"|awk '/Slave_SQL_Running/{FS=":";print $2}'`

#nagios status:
STATE_OK=0
STATE_WARNING=1
STATE_CRITICAL=2
STATE_UNKNOWN=3
STATE_DEPENDENT=4

if [ "$Slave_IO_Running" != "Yes" -o "$Slave_SQL_Running" != "Yes" ];then
        echo "Slave_IO or Slave_SQL process exited! Last_IO_Errno:$Last_IO_Errno Last_SQL_Errno:$Last_SQL_Errno"
        exit $STATE_CRITICAL
fi

later=$[$Read_Master_Log_Pos - $Exec_Master_Log_Pos]

if [ $later -ge 100 ];then
        echo "Read_Master_Log_Pos:$Read_Master_Log_Pos Exec_Master_Log_Pos:$Exec_Master_Log_Pos"
        exit $STATE_WARNING
fi

if [ $Seconds_Behind_Master -ge 30 ];then
        echo "Warning: Seconds_Behind_Master:$Seconds_Behind_Master"
        exit $STATE_WARNING
fi
echo "Slave OK-Read_Master_Log_Pos:$Read_Master_Log_Pos Exec_Master_Log_Pos:$Exec_Master_Log_Pos Seconds_Behind_Master:$Seconds_Behind_Master"
exit $STATE_OK
