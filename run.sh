#!/bin/bash

service mysql restart

cd dev/server/apigateway 
mvn clean install spring-boot:run &
cd -
cd dev/server/authpub
mvn clean install spring-boot:run &
cd -
cd dev/server/publishing
mvn clean install tomcat7:run &
cd -
cd dev/server/listing
mvn clean install tomcat7:run &

