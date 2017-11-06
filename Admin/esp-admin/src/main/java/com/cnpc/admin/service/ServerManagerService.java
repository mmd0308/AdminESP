package com.cnpc.admin.service;

import com.cnpc.admin.entity.ServerManager;
import com.cnpc.admin.mapper.ServerManagerMapper;
import com.cnpc.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


@Service
public class ServerManagerService extends BaseService<ServerManagerMapper,ServerManager> {
    public  final String  CONTAINER_URL_LO = "http://localhost:5556/v1.24";
    public  final String  CONTAINER_URL_66 = "http://10.27.213.66:5556/v1.24";
    public  final String  CONTAINER_URL_67 = "http://10.27.213.67:5556/v1.24";
    public  final String  CONTAINER_URL_68 = "http://10.27.213.68:5556/v1.24";
    public  final String  CONTAINER_URL_69 = "http://10.27.213.69:5556/v1.24";
    public  final String  CONTAINER_URL_70 = "http://10.27.213.70:5556/v1.24";


    public  final String  HTTP_URL = "http://localhost:5556/v1.24";

    @Autowired
    private RestTemplate restTemplate;


    public ResponseEntity<Object> getImagesRepo(HashMap map) {
        ResponseEntity<Object> forEntity = restTemplate.getForEntity(HTTP_URL + "/images/json?all={all}", Object.class, map);
        return forEntity;
    }
}
