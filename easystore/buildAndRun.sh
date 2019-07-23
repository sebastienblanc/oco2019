#!/bin/sh
mvn clean package && docker build -t org.sebjef/acceptance .
docker rm -f acceptance || true && docker run -d -p 8080:8080 -p 4848:4848 --name acceptance org.sebjef/acceptance 
