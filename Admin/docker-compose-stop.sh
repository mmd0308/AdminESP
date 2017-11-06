#!/bin/bash

# 编译所有的代码，生成docker 镜像
function progress(){
    local GREEN CLEAN
    GREEN='\033[0;32m'
    CLEAN='\033[0m'
    printf "\n${GREEN}$@  ${CLEAN}\n" >&2
}

set -e

docker-compose down &

docker-compose -f docker-compose-gateway.yml down
