#! /bin/bash

. setEnv.sh

echo "Undeploying Easystore application"
asadmin undeploy easystore

echo "Stopping Payara Server"
asadmin stop-domain production
