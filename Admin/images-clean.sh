#!/bin/bash

# 将本地镜像推送到私有仓库
set -e

# 镜像仓库前缀
#REGPREFIX=10.27.213.66:5000/adminesp
REGPREFIX=127.0.0.1:5000/adminesp

#docker rmi ${REGPREFIX}/esp-registry
#docker rmi ${REGPREFIX}/esp-config
#docker rmi ${REGPREFIX}/esp-gateway
#docker rmi ${REGPREFIX}/esp-admin

#docker rmi ${docker images -q}

# docker rmi $(docker images | awk '/^<none>/ { print $3 }')

docker images | grep ${REGPREFIX} | awk '{print "docker rmi "$1":"$2}' | sh
docker images




