TOMCAT_HOME=/home/zcy/dev/tomcat/apache-tomcat-8.0.28-18080
#TOMCAT_HOME=$(pwd)
echo $TOMCAT_HOME
ps -ef | grep $TOMCAT_HOME |grep -v grep |awk {'print $2'} | sed -e "s/^/kill -9 /g" | sh -
echo "-----rm work start-----"
rm  $TOMCAT_HOME/work/* -rf
echo "-----rm work end-----"
nohup $TOMCAT_HOME/bin/startup.sh &
sleep 1
tail -f $TOMCAT_HOME/logs/catalina.out
