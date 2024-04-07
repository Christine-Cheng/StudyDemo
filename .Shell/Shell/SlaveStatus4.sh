#!/bin/bash
HOSTNAME="localhost"
PORT="3306"
USERNAME="root"
PASSWORD="root@sql"
DBNAME="test"
use_db_sql="use ${DBNAME}"

select_sql="show slave status\G;"
state=$(mysql -h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} ${DBNAME} -e "${select_sql}" 2>/dev/null)

#提取系统变量
slave_sql_running=$(mysql -h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} ${DBNAME} -e "${select_sql}" 2>/dev/null | awk -n -F": " '{if ($1 ~/Slave_SQL_Running$/) print $2}')
slave_io_running=$(mysql -h${HOSTNAME} -P${PORT} -u${USERNAME} -p${PASSWORD} ${DBNAME} -e "${select_sql}" 2>/dev/null | awk -n -F": " '{if ($1 ~/Slave_IO_Running$/) print $2}')

#这里打出需要判断的系统变量
echo $slave_sql_running
echo $slave_io_running

#Todo：
#判断状态是否出错发邮件
