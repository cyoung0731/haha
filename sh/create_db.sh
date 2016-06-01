#!/bin/bash
##############################
# @file mysql.sh
# @create database
# @author zcy
# @version 0.1
# @date 2013-01-20
##############################
HOSTNAME="192.168.142.128"
PORT="3306"
DB_USER="root"
DB_PASSWD="root"
DATABASE="shiordb"
NEW_USER="shior"
NEW_PASSWD="shior"



######################
echo "cylog----- create DB start -----"
mysql -h$HOSTNAME -P$PORT -u$DB_USER -p$DB_PASSWD << EOF 
CREATE DATABASE $DATABASE DEFAULT CHARSET=utf8;
EOF
[ $? -eq 0 ] && echo "cylog----- created DB success. -----" || echo "cylog----- create DB failed... -----"

echo "cylog----- create user start -----"
mysql -h$HOSTNAME -P$PORT -u$DB_USER -p$DB_PASSWD << EOF 
CREATE USER '$NEW_USER'@'%' IDENTIFIED BY '$NEW_PASSWD';
CREATE USER '$NEW_USER'@'localhost' IDENTIFIED BY '$NEW_PASSWD';
grant all privileges on $DATABASE.* to $NEW_USER@'%' identified by '$NEW_PASSWD';
grant all privileges on $DATABASE.* to $NEW_USER@'localhost' identified by '$NEW_PASSWD';
flush privileges;
EOF
[ $? -eq 0 ] && echo "cylog----- created user success. -----" || echo "cylog----- create user failed... -----"
