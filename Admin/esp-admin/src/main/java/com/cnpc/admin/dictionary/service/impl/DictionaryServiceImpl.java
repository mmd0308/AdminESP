package com.cnpc.admin.dictionary.service.impl;

import com.cnpc.admin.dao.DAO;
import com.cnpc.admin.dictionary.service.IDictionaryService;
import com.cnpc.admin.entity.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("dictionaryService")
public class DictionaryServiceImpl implements IDictionaryService {
    @Resource(name = "daoSupport")
    private DAO dao;

    @Override
    public List<PageData> find(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("DictionaryMapper.find",pd);
    }

    @Override
    public void deletedById(PageData pd) throws Exception {
        dao.delete("DictionaryMapper.deleted",pd);
    }

    @Override
    public void update(PageData pd) throws Exception {
        dao.update("DictionaryMapper.update",pd);
    }

    @Override
    public void save(PageData pd) throws Exception {
        dao.save("DictionaryMapper.save",pd);
    }
}
