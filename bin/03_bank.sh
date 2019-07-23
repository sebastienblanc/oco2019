#! /bin/bash

clear

. ./setEnv.sh

BANK_PORT=8082

echo "Starting SmartBank with Payara Micro. Hollow jar deployment. Port $BANK_PORT"

echo "2 involved files:"
du -h $SMARTBANK_WAR_FILE
du -h $PAYARA_MICRO_HOME/payara-micro-5.192.jar

echo "Running SmartBank on port $BANK_PORT:"

CMD_BANK="java -jar --add-opens=java.base/jdk.internal.loader=ALL-UNNAMED  $PAYARA_MICRO_HOME/payara-micro-5.192.jar --noCluster --addJars $HSQLDB_HOME/lib/hsqldb.jar --port $BANK_PORT --deploy $SMARTBANK_WAR_FILE"

echo "Running command, type enter when ready:"
echo $CMD_BANK

read

$CMD_BANK
