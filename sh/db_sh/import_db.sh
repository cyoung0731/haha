HOSTNAME="192.168.142.128"
PORT="3306"
DB_NAME="dongyadb"
DB_USER="dongya"
DB_PASSWD="dongya"
IMPORT_DIR="/home/mysql/temp/db_sql_file"
IMPORT_FILE="$IMPORT_DIR/doitdb.sql"

mysql -h$HOSTNAME -P$PORT -u$DB_USER -p$DB_PASSWD $DB_NAME < $IMPORT_FILE

if [ $? -eq 0 ]; then
	echo "cylog----- import $DB_NAME success -----"
else 
	echo "cylog----- import $DB_NAME failed -----"
fi
