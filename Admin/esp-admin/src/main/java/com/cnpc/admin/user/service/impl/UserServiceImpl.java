package com.cnpc.admin.user.service.impl;

import com.cnpc.admin.basic.dao.BaseDao;
import com.cnpc.admin.entity.PageData;
import com.cnpc.admin.user.service.UserService;
import com.cnpc.admin.utils.UUIDUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource(name = "daoSupport")
    private BaseDao dao;

    @Override
    public List<PageData> find(PageData pd) throws Exception {
        return (List<PageData>) dao.findForList("UserMapper.find",pd);
    }

    @Override
    @Transactional
    public void deletedById(PageData pd) throws Exception {
        dao.delete("UserMapper.deleted",pd);
    }

    @Override
    @Transactional
    public void update(PageData pd) throws Exception {
        dao.update("UserMapper.update",pd);
    }

    @Override
    @Transactional
    public void save(PageData pd) throws Exception {
        //密码加密
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        String password = encoder.encode(pd.getString("password"));
        pd.put("password",password);
        pd.put("id", UUIDUtil.get32UUID());

        dao.save("UserMapper.save",pd);
    }
}