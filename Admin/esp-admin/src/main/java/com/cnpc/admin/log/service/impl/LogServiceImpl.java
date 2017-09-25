package com.cnpc.admin.log.service.impl;

import com.cnpc.admin.basic.dao.BaseDao;
import com.cnpc.admin.entity.PageData;
import com.cnpc.admin.log.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("logService")
public class LogServiceImpl implements LogService {
    @Resource(name = "daoSupport")
    private BaseDao dao;


    @Override
    public List<PageData> find(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("LogMapper.list",pd);
    }

    @Override
    public void deletedById(PageData pd) throws Exception {

    }

    @Override
    public void update(PageData pd) throws Exception {

    }

    @Override
    public void save(PageData pd) throws Exception {
        dao.save("LogMapper.save",pd);
    }
}
