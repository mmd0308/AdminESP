package com.cnpc.admin.service;

import com.cnpc.common.constant.UserConstant;
import com.cnpc.common.service.BaseService;
import com.cnpc.admin.entity.User;
import com.cnpc.admin.mapper.UserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-9-26
 */
@Service
public class UserService extends BaseService<UserMapper,User> {

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    public User getUserByUsername(String username){
        User user = new User();
        user.setUsername(username);
        return this.selectOne(user);
    }


    public void insertSelective(User entity) {
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getPassword());
        entity.setPassword(password);
        super.insertSelective(entity);
    }



}
