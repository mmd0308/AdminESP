package com.cnpc.ucenter.service;

import com.cnpc.common.constant.UserConstant;
import com.cnpc.common.service.BaseService;
import com.cnpc.ucenter.entity.Org;
import com.cnpc.ucenter.entity.User;
import com.cnpc.ucenter.mapper.OrgMapper;
import com.cnpc.ucenter.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrgService extends BaseService<OrgMapper,Org> {


    public List<Org> getTree(Org org) {
        List<Org> orgs = mapper.getOrgTree(org);
        return orgs;
    }
}
