#!/bin/bash
##############################
# @file mysql.sh
# @drop database
# @author zcy
# @version 0.1
# @date 2013-01-20
##############################
HOSTNAME="192.168.142.128"
PORT="3306"
DB_USER="root"
DB_PASSWD="root"
DATABASE="dongyadb"
NEW_USER=dongya
NEW_PASSWD=dongya

######################
echo "cylog----- drop DB start -----"
mysql -h$HOSTNAME -P$PORT -u$DB_USER -p$DB_PASSWD << EOF 
DROP DATABASE $DATABASE;
EOF
[ $? -eq 0 ] && echo "cylog----- drop DB success. -----" || echo "cylog----- drop DB failed... -----"


echo "cylog----- drop user start -----"
mysql -h$HOSTNAME -P$PORT -u$DB_USER -p$DB_PASSWD << EOF 
DROP user $NEW_USER@'%';
DROP user $NEW_USER@'localhost';
EOF
[ $? -eq 0 ] && echo "cylog----- drop user success. -----" || echo "cylog----- drop user failed... -----"
