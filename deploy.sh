BUILD_DIR=/e/dev/git/haha/build
DEPLY_DIR=/c/dev/tomcat/deploy
WEBAPP_CONTEXT=haha
WAR_NAME=haha-1.0-SNAPSHOT.war

gradle clean build
mkdir -p $DEPLY_DIR/$WEBAPP_CONTEXT
cp $BUILD_DIR/libs/$WAR_NAME $DEPLY_DIR/$WEBAPP_CONTEXT
cd $DEPLY_DIR/$WEBAPP_CONTEXT
jar -xf $WAR_NAME
rm -f $WAR_NAME
