HOSTNAME="192.168.18.21"
PORT="3306"
DB_NAME="doit"
DB_USER="dongya"
DB_PASSWD="dongya"
EXPORT_DIR="/home/mysql/temp/db_sql_file"
EXPORT_FILE="$EXPORT_DIR/doitdb.sql"

if [ ! -d "$EXPORT_DIR" ]; then
        mkdir $EXPORT_DIR
fi

if [ -f "$EXPORT_FILE" ]; then
	mv $EXPORT_FILE "$EXPORT_FILE"_bak$(date +%Y%m%d%H%M%S)
fi

mysqldump -h$HOSTNAME -P$PORT -u$DB_USER -p$DB_PASSWD $DB_NAME > $EXPORT_FILE


if [ $? -eq 0 ]; then
        echo "cylog----- export $DB_NAME success -----"
else
        echo "cylog----- export $DB_NAME failed -----"
fi
