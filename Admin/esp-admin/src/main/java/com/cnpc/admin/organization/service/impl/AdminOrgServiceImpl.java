package com.cnpc.admin.organization.service.impl;

import com.cnpc.admin.dao.DAO;
import com.cnpc.admin.entity.PageData;
import com.cnpc.admin.organization.service.IAdminOrgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("adminOrgService")
public class AdminOrgServiceImpl implements IAdminOrgService {
    @Resource(name = "daoSupport")
    private DAO dao;

    @Override
    public List<PageData> findAll() throws Exception {
        List<PageData> list = (List<PageData>) dao.findForList("LogMapper.findAll", null);
        return list;
    }
}
