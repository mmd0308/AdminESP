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

progress "starting esp-registry ..."
cd esp-registry
mvn spring-boot:run  &
cd -

progress "starting esp-config ..."
cd esp-config
mvn spring-boot:run &
cd -


progress "starting esp-admin ..."
cd esp-admin
mvn spring-boot:run &
cd -

sleep 30
progress "starting esp-gateway ..."
cd esp-gateway
mvn spring-boot:run &
cd -
