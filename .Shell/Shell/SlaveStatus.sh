#!/bin/bash
#[root@A mysql]# cat check_slave_status.sh
#!/bin/sh
#--------------------------------------------
#Author: Created by randolph 2016-08-17.
#Function: This scripts function is"Monitoring MySQL Master-Slave Status".
#Version:4.1.2
#---------------------------------------------
ERRORNO=(1158 1158 1008 1007 1062)
MySQL_CMD="mysql -uroot -p888888 -S /data/3308/mysql.sock"
while true; do
	array=($($MySQL_CMD-e "show slave status\G" | egrep"Running|Behind_Master|Last_SQL_Errno" | awk-F ":" '{print $NF}'))
	if [ "${array[0]}" == "Yes" -a "${array[1]}" == "Yes" -a "${array[2]}" == "0" ]; then
		echo"MySQL salve status is OK."
	else
		for ((i = 0; i < ${#ERRORNO[*]}; i++)); do
			if [ "${array[3]}" == "${ERRORNO[$i]}" ]; then
				$MySQL_CMD-e "stop slave;set globalsql_slave_skip_counter=1;start slave;"
			fi
		done
		chars="MySQL salve status is FAIED."
		echo"$chars"
		echo"$chars" | mail -s "$chars" 813415154@qq.com
	fi
	sleep 3
done
