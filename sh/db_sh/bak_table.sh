HOSTNAME="192.168.142.128"
PORT="3306"
DB_NAME="dongyadb"
DB_USER="dongya"
DB_PASSWD="dongya"
RESULT_FILE_DIR="/home/zcy/dev/temp"
DROP_RESULT_FILE="$RESULT_FILE_DIR/drop.sql"
BAK_RESULT_FILE="$RESULT_FILE_DIR/bak.sql"

function select_bak_all_table_sql(){
        #-Ne忽略表头
        mysql -h$HOSTNAME -P$PORT -u$DB_USER -p$DB_PASSWD $DB_NAME -Ne "select concat('create table ',table_name,'_bak',date_format(now(),'%Y%m%d'),' as select * from ',table_name,';') from information_schema.tables where table_schema =
 '$DB_NAME';" > $BAK_RESULT_FILE
        if [ $? -eq 0 ]; then
                echo "cylog----- select_bak_table_sql success -----"
        else
                echo "cylog----- select_bak_table_sql failed -----"
                exit 1
        fi
}

function back_all_table(){
        mysql -h$HOSTNAME -P$PORT -u$DB_USER -p$DB_PASSWD $DB_NAME < $BAK_RESULT_FILE
        if [ $? -eq 0 ]; then
                echo "cylog----- bak table success -----"
        else
                echo "cylog----- bak table failed -----"
                exit 1
        fi
}

function select_drop_table_sql(){
        mysql -h$HOSTNAME -P$PORT -u$DB_USER -p$DB_PASSWD $DB_NAME -Ne "select concat('drop table ',table_name,';') from information_schema.tables where table_schema = '$DB_NAME';" > $DROP_RESULT_FILE
        if [ $? -eq 0 ]; then
                echo "cylog----- select drop_table success -----"
        else
                echo "cylog----- select drop_table failed -----"
                exit 1
        fi
}

function drop_table(){
        mysql -h$HOSTNAME -P$PORT -u$DB_USER -p$DB_PASSWD $DB_NAME < $DROP_RESULT_FILE
        if [ $? -eq 0 ]; then
                echo "cylog----- drop_table success -----"
        else
                echo "cylog----- drop_table failed -----"
                exit 1
        fi
}

function drop_bak_table(){
        mysql -h$HOSTNAME -P$PORT -u$DB_USER -p$DB_PASSWD $DB_NAME -Ne "select concat('drop table ',table_name,';') from information_schema.tables where table_schema = '$DB_NAME' and table_name like '%_bak%';" > $DROP_RESULT_FILE
        if [ $? -eq 0 ]; then
                echo "cylog----- select drop_bak_table sql success -----"
        else
                echo "cylog----- select drop_bak_table sql failed -----"
                exit 1
        fi
        mysql -h$HOSTNAME -P$PORT -u$DB_USER -p$DB_PASSWD $DB_NAME < $DROP_RESULT_FILE
        if [ $? -eq 0 ]; then
                echo "cylog----- drop_bak_table success -----"
        else
                echo "cylog----- drop_bak_table failed -----"
                exit 1
	fi
}

if [ ! -d "$RESULT_FILE_DIR" ]; then
        mkdir $RESULT_FILE_DIR
fi

if [ -f "$DROP_RESULT_FILE" ]; then
        mv $DROP_RESULT_FILE "$DROP_RESULT_FILE"_bak$(date +%Y%m%d%H%M%S)
fi

if [ -f "$BAK_RESULT_FILE" ]; then
        mv $BAK_RESULT_FILE "$BAK_RESULT_FILE"_bak$(date +%Y%m%d%H%M%S)
fi

drop_bak_table
select_bak_all_table_sql
select_drop_table_sql
back_all_table
drop_table
