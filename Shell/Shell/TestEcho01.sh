#!/bin/bash
# 字符串echo命令

#字符串输出
echo string

#显示普通字符串
echo "It is a test"

#转义字符
echo "\"It is a test\""

#显示变量
read name
echo "my lover is $name"

#显示行转换
echo -e "OK! \n" # -e 开启转义
echo "开启转义It is a test"

#显示不换行
echo -e "OK! \c" # -e 开启转义 \c 不换行
echo "不换行It is a test"

#显示结果定向至文件
echo "显示结果定向至文件It is a test" >myfile

#原样输出字符串，不进行转义或取变量(用单引号)
echo '$name\"'

#显示命令执行结果; 这里使用的是反引号 `, 而不是单引号 '
echo $(date)
