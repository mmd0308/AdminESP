#!/bin/bash

# 将本地镜像推送到私有仓库
set -e

# 镜像仓库前缀
REGPREFIX=127.0.0.1:5000/adminesp
REMOTE=10.27.213.66:5000/adminesp

#registry
docker tag ${REGPREFIX}/esp-registry ${REMOTE}/esp-registry
docker push ${REMOTE}/esp-registry

docker tag ${REGPREFIX}/esp-config ${REMOTE}/esp-registry
docker push ${REMOTE}/esp-config


docker tag ${REGPREFIX}/esp-gateway ${REMOTE}/esp-registry
docker push ${REMOTE}/esp-gateway

docker tag ${REGPREFIX}/esp-admin ${REMOTE}/esp-registry
docker push ${REMOTE}/esp-admin


#docker push ${docker images -q|grep 10.27.213.66:5000/adminesp}
# see http://blog.csdn.net/cuipengchong/article/details/70917177
# 批量推送
#docker images | grep ${REMOTE} | awk '{print "docker push "$1":"$2}' | sh

docker images | grep ${REMOTE} | awk '{print "docker rmi "$1":"$2}' | sh