package com.cnpc.admin.service;

import com.cnpc.common.service.BaseService;
import com.cnpc.admin.entity.Org;
import com.cnpc.admin.mapper.OrgMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrgService extends BaseService<OrgMapper,Org> {


    public List<Org> getTree(Org org) {
        List<Org> orgs = mapper.getOrgTree(org);
        return orgs;
    }
}
