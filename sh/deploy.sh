SRC_DIR=/home/zcy/dev/git/myproject
#DEPLOY_DIR=/e/zcy/dev/tomcat/deploy/myproject
DEPLOY_DIR=/home/zcy/dev/tomcat/deploy/myproject
TOMCAT_HOME=/home/zcy/dev/tomcat/apache-tomcat-8.0.28-18080

function mvn_package() {
	echo "cylog----- mvn clean package start -----"
	cd $SRC_DIR
	mvn clean package
	if [ $? -ne 0 ]
	    then
	    echo "cylog----- mvn clean package failed -----"
	    exit 1
	else
	    echo "cylog----- mvn clean package success -----"
	fi
}

function stop_tomcat() {
	echo "cylog----- stop tomcat start-----"
	ps -ef | grep $TOMCAT_HOME |grep -v grep |awk {'print $2'} | sed -e "s/^/kill -9 /g" | sh -
	echo "cylog----- stop tomcat end-----"
}

function mv_war() {
	echo "cylog----- mv war to deploy dir start -----"
	mkdir -p $DEPLOY_DIR
	mkdir -p $DEPLOY_DIR/../../webapp_bak
	cp -r $DEPLOY_DIR $DEPLOY_DIR/../../webapp_bak/myproject_bak$(date +%Y%m%d%H%M%S)
	cd $DEPLOY_DIR/../
	rm -rf *
	cd $SRC_DIR
	mkdir -p $DEPLOY_DIR
	cp target/*.war $DEPLOY_DIR
	cd $DEPLOY_DIR
	jar -xf *.war
	rm -f *.war
	echo "cylog----- mv war to deploy dir end-----"
}


#--------main process start-----------
if [ -n "$1" ] ; then
	if [ $1 = "-p" ] ; then
	        mvn_package
	fi
fi

stop_tomcat
mv_war
#--------mian process end------------
