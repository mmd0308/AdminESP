package com.cnpc.admin.service;

import com.cnpc.admin.entity.ServerManager;
import com.cnpc.admin.mapper.ServerManagerMapper;
import com.cnpc.common.constant.ServerConstants;
import com.cnpc.common.message.ObjectRestResponse;
import com.cnpc.common.service.BaseService;
import com.cnpc.common.util.JsonUtil;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ServerManagerService extends BaseService<ServerManagerMapper,ServerManager> {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取仓库的进行，以及对应服务器运行的容器
     * @param map
     * @return
     */
    public List<JSONObject> getImagesRepo(HashMap map) {
        //获取镜像仓库所有的镜像
        List<String> repositories = getAllImagesNameToRpo();
        List<JSONObject>  resbody = new ArrayList<>();
        for (String str : repositories){

            //获取镜像的版本
            JSONObject body = getALlImagesTagsToRpoByName(str);
            List<String> tags = (List<String>) body.get("tags");
            for (String tag : tags){
                JSONObject resMap = new JSONObject();
                resMap.put("name", (String) body.get("name"));
                //获取镜像id
                String imageId = getImageIdToRpoByNameAndTag(str,tag);
                resMap.put("tag",tag);
                resMap.put("imageId",imageId);
                //获取所有服务器的ip
                List<String> serverIp = getServerIp();
                List<Map<String,Object>> resCon = new ArrayList<Map<String,Object>>();
                for (String ip: serverIp) {
                    //根据镜像id，获取容器信息
                    ResponseEntity conByImageId = this.getConByImageIdAndIp(imageId,ip);
                    List<Map<String,Object>> containers= (List<Map<String, Object>>) conByImageId.getBody();
                    for (Map<String,Object> m : containers){
                        Map<String,Object> res = new HashMap<>();
                        res.put("cNames", m.get("Names"));
                        res.put("cId",m.get("Id"));
                        res.put("cIp",m.get("Ports"));
                        res.put("cCreated",m.get("Created"));
                        res.put("cState",m.get("State"));
                        res.put("cStatus",m.get("Status"));
                        res.put("sIp",ip);
                        resCon.add(res);
                    }
                }
                resMap.put("containers",resCon);
                resbody.add(resMap);
            }
        }
        return resbody;
    }

    /**
     * 根据镜像id,服务器Ip 获取镜像容器
     * @param imgId
     * @return
     */
    public ResponseEntity getConByImageIdAndIp(String imgId,String ip){
        HashMap<String ,Object> map = new HashMap<>();
        String [] arr ={imgId};
        map.put("ancestor",arr);
        map.put("filters", JsonUtil.ObjectToJson(map));
        return restTemplate.getForEntity("http://"+ip+"/v1.24/containers/json?filters={filters}&all=1",Object.class,map);
    }

    /**
     * 根据精细那个版本，
     * @return
     */
    public ResponseEntity<JSONObject> getImagesByNameAndTag(String name,String tag){
        return  null;
    }

    /**
     * 获取所有的镜像名称
     * @return
     */
    public List<String> getAllImagesNameToRpo(){
        ResponseEntity<JSONObject> forEntity = restTemplate.getForEntity(ServerConstants.DOCKER_REGISTORY+"/_catalog", JSONObject.class);
        HashMap<String,List<String>> rebody = (HashMap<String, List<String>>) forEntity.getBody();
        return rebody.get("repositories");
    }

    /**
     * 获取仓库所有的镜像以及版本
     * @param name
     * @return
     */
    public JSONObject getALlImagesTagsToRpoByName(String name){
        String url = ServerConstants.DOCKER_REGISTORY+"/"+name+"/tags/list";
        ResponseEntity<JSONObject> resForEntity = restTemplate.getForEntity(url, JSONObject.class);
        return resForEntity.getBody();
    }

    /**
     * 根据镜像名称和版本获取仓库镜像id
     * @param name
     * @param tag
     * @return
     */
    public String getImageIdToRpoByNameAndTag(String name,String tag){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept","application/vnd.docker.distribution.manifest.v2+json");
        String allUrl = ServerConstants.DOCKER_REGISTORY+"/"+name+"/manifests/"+tag;
        HttpEntity<String> entity = new HttpEntity<>(null,headers);
        ResponseEntity<JSONObject> forEntityInspect = restTemplate.exchange(allUrl, HttpMethod.GET, entity, JSONObject.class);
        JSONObject inspectBody = forEntityInspect.getBody();
        HashMap<String,String> config = (HashMap<String, String>) inspectBody.get("config");
        return config.get("digest");
    }

    /**
     * 获取所有服务器的ip地址
     * @return
     */
    public List<String> getServerIp(){
        List<String> reslist = new ArrayList<>();
        reslist.add(ServerConstants.CONTAINER_URL_66);
        reslist.add(ServerConstants.CONTAINER_URL_67);
       // reslist.add(ServerConstants.CONTAINER_URL_68);
        reslist.add(ServerConstants.CONTAINER_URL_69);
        reslist.add(ServerConstants.CONTAINER_URL_70);
        return reslist;
    }


    public Object getConByImageId(String imgId) {
        return null;
    }

    public void conStop(String id,String sIp) {
        restTemplate.postForLocation("http://" + sIp + "/v1.24/containers/" + id + "/stop",null);
    }

    public void conStart(String id, String sIp) {
        restTemplate.postForLocation("http://" + sIp + "/v1.24/containers/" + id + "/start",null);
    }

    public void conRmove(String id,String sIp) {
        restTemplate.delete("http://"+ sIp + "/v1.24/containers/" + id);
    }
}
