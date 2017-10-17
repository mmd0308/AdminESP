package com.cnpc.admin.service;

import com.cnpc.common.service.BaseService;
import com.cnpc.admin.entity.Org;
import com.cnpc.admin.mapper.OrgMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class OrgService extends BaseService<OrgMapper,Org> {


    public List<Map> getTree(Org org) {
        List<Map> orgs = this.getOrgByPIDToMap(org.getParentid());
        return this.getTrees(orgs);
    }

    /**
     * 根据一级节点获取树
     * @param orgs
     * @return
     */
    public List<Map> getTrees(List<Map> orgs){
        for (Map o: orgs ) {
            List<Map> orgByPID = this.getOrgByPIDToMap(o.get("id").toString());
            if (orgByPID.size()>0){
                o.put("children",orgByPID);
                getTrees(orgByPID);
            }
        }
        return  orgs;
    }
    /**
     * 根据父id，获取列表数据 返回List<map>
     * @param pid
     * @return
     */
    public List<Map> getOrgByPIDToMap(String pid){
        return mapper.getOrgByPIDToMap(pid);
    }

    public String getNextLevelCode(String parentId,String levelCode) {
        String lCode = mapper.getLevelCodeByParentId(parentId);
        if(null != lCode && lCode != ""){
            lCode = Integer.parseInt(lCode.substring(lCode.length()-6))+1+"";
            String str = "";
            int num = 6-lCode.length()%6;
            for (int i=0;i<num;i++){
                str = str + "0";
            }
            lCode = str + lCode;
        }else {
            lCode = "000001";
        }
        return  levelCode+lCode;
    }
}
