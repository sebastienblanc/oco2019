#! /bin/bash

clear

. ./setEnv.sh

echo "Starting EasyStore with Payara Server. Thin war deployment"

echo "1 file involved:"
du -h $EASYSTORE_WAR_FILE

cat /dev/null > $PAYARA_HOME/glassfish/domains/production/logs/server.log
echo "Deploying on Payara Server Full 5.192:"
du -sh $PAYARA_HOME

# To be made one time
# cp $HSQLDB_HOME/lib/hsqldb.jar $PAYARA_HOME/glassfish/domains/production/lib

# Not possible on production domain
# cp $WAR_FILE $PAYARA_HOME/glassfish/domains/production/autodeploy

echo "Starting Payara Server on ports 4848 (admin) and 8080 (apps)"

START_SERVER_CMD="asadmin start-domain production"
echo "Starting server on production domain:"
echo $START_SERVER_CMD
$START_SERVER_CMD

DEPLOY_APP_CMD="asadmin deploy $EASYSTORE_WAR_FILE"
echo "Deploying Easystor application::"
echo $DEPLOY_APP_CMD
$DEPLOY_APP_CMD
