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

progress "Stopping all the running spring-boot ..."
ps -efww|grep -w 'spring-boot:run'|grep -v grep|cut -c 9-15|xargs kill -9
progress "All the running spring-boot stopped..."
