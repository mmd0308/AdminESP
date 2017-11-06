#!/bin/bash

# 编译所有的代码，生成docker 镜像
function progress(){
    local GREEN CLEAN
    GREEN='\033[0;32m'
    CLEAN='\033[0m'
    printf "\n${GREEN}$@  ${CLEAN}\n" >&2
}

set -e

# 镜像仓库前缀
REGPREFIX=127.0.0.1:5000/adminesp
#REGPREFIX=10.27.213.66:5000/adminesp
TOTAL=4
INDEX=0

# common
progress "Building esp-common jar file ..."
# mvn clean compile
cd esp-common
mvn install
cd -

#registry server
INDEX=$[INDEX+1]
progress "Building discovery-server(${INDEX}/${TOTAL}) jar file ..."
cd esp-registry
mvn clean package -DskipTests
progress "Building discovery(eureka server)(${INDEX}/${TOTAL}) docker image ..."
docker tag $(docker build -t ${REGPREFIX}/esp-registry -q .) ${REGPREFIX}/esp-registry:$(date -u "+%Y%m%d-%H%M%S")
cd -

#config server
INDEX=$[INDEX+1]
progress "Building config-server(${INDEX}/${TOTAL}) jar file ..."
cd esp-config
mvn clean package -DskipTests
progress "Building config server(${INDEX}/${TOTAL}) docker image ..."
docker tag $(docker build -t ${REGPREFIX}/esp-config -q .) ${REGPREFIX}/esp-config:$(date -u "+%Y%m%d-%H%M%S")
cd -

#gateway
INDEX=$[INDEX+1]
progress "Building gateway-zuul(${INDEX}/${TOTAL}) jar file ..."
cd esp-gateway
mvn clean package -DskipTests
progress "Building gateway-zuul(${INDEX}/${TOTAL}) docker image ..."
docker tag $(docker build -t ${REGPREFIX}/esp-gateway -q .) ${REGPREFIX}/esp-gateway:$(date -u "+%Y%m%d-%H%M%S")
cd -


#admin
INDEX=$[INDEX+1]
progress "Buildinhg esp-admin service(${INDEX}/${TOTAL}) jar file ..."
cd esp-admin
mvn clean package -DskipTests
progress "Building esp-admin service(${INDEX}/${TOTAL}) docker image ..."
docker tag $(docker build -t ${REGPREFIX}/esp-admin -q .) ${REGPREFIX}/esp-admin:$(date -u "+%Y%m%d-%H%M%S")
cd -
