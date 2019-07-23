#! /bin/bash

clear

. ./setEnv.sh

echo "Buildling SmartBank UberJar with Payara Micro:"

BANK_PORT=8082

CMD_BANK="java -jar $PAYARA_MICRO_HOME/payara-micro-5.192.jar --noCluster --addJars $HSQLDB_HOME/hsqldb.jar --port $BANK_PORT --deploy $APP_HOME/bank.war --outputUberJar bank-uber.jar"

echo $CMD_BANK

read

$CMD_BANK
