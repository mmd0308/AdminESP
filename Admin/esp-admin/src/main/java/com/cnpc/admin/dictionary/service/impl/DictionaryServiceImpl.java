package com.cnpc.admin.dictionary.service.impl;

import com.cnpc.admin.basic.dao.DAO;
import com.cnpc.admin.dictionary.service.IDictionaryService;
import com.cnpc.admin.entity.PageData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public void deletedById(PageData pd) throws Exception {
        dao.delete("DictionaryMapper.deleted",pd);
    }

    @Override
    @Transactional
    public void update(PageData pd) throws Exception {
        dao.update("DictionaryMapper.update",pd);
    }

    @Override
    @Transactional
    public void save(PageData pd) throws Exception {
        dao.save("DictionaryMapper.save",pd);
    }

    @Override
    public List<PageData> findOnlyCode(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("DictionaryMapper.findOnlyCode",pd);
    }
}
