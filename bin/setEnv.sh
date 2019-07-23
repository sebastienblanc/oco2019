#! /bin/bash

EASYSTORE_WAR_FILE=~/javadev/demo/oco-2019/easystore/target/easystore.war
SMARTBANK_WAR_FILE=~/javadev/demo/oco-2019/smartbank/target/bank.war
MERCHANTBO_JAR_FILE=~/javadev/demo/oco-2019/merchantbo/target/merchantbo-1.0.jar

export HSQLDB_HOME=~/javatools/hsqldb-2.5.0
export PAYARA_MICRO_HOME=~/javatools/payara-micro-5.192
export PAYARA_HOME=~/javatools/payara-5.192
export DATA_HOME=~/javadev/data

export PATH=$PAYARA_HOME/glassfish/bin:$PATH

export PATH=.:$PATH
