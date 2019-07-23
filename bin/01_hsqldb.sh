#! /bin/bash

. setEnv.sh

rm -rf $DATA_HOME/hsqldb 
echo "Starting HSQL Database Server in verbose mode"

java -cp $HSQLDB_HOME/lib/hsqldb.jar org.hsqldb.server.Server --silent false --database.0 $DATA_HOME/hsqldb/devoxx --dbname.0 devoxx
