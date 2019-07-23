#! /bin/bash

clear

. ./setEnv.sh

echo "Starting Merchant Back Office with KumuluzEE. Fat jar deployment. Port 8081"

echo "1 file involved:"
du -h $MERCHANTBO_JAR_FILE

CMD_BO="java -jar $MERCHANTBO_JAR_FILE"

echo "Running command:"
echo $CMD_BO
read

$CMD_BO
