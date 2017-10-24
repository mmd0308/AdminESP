package com.cnpc.admin.service;

import com.cnpc.common.constant.UserConstant;
import com.cnpc.common.message.TableResultResponse;
import com.cnpc.common.service.BaseService;
import com.cnpc.admin.entity.User;
import com.cnpc.admin.mapper.UserMapper;
import com.cnpc.common.util.Query;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.RequestContext;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * @author billjiang 475572229@qq.com
 * @create 17-9-26
 */
@Service
public class UserService extends BaseService<UserMapper, User> {

    /**
     * 根据用户名获取用户信息
     *
     * @param username
     * @return
     */

    @Autowired
    private HttpSession session;

    public User getUserByUsername(String username) {
        if (session != null && session.getAttribute("user") != null) {
            return (User) session.getAttribute("user");
        } else {
            User user = new User();
            user.setUsername(username);
            user = this.selectOne(user);
            session.setAttribute("user", user);
            return user;
        }
    }


    public void insertSelective(User entity) {
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getPassword());
        entity.setPassword(password);
        super.insertSelective(entity);
    }

    public TableResultResponse<User> selectByName(Query query) {
        Example example = new Example(User.class);
        Example.Criteria criteria1 = example.createCriteria();
        Example.Criteria criteria2 = example.createCriteria();
        if (query.get("name") != null) {
            criteria1.andLike("name", "%" + query.get("name").toString() + "%");
            criteria2.andLike("username", "%" + query.get("name").toString() + "%");
        }
        example.or(criteria2);
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<User> list = mapper.selectByExample(example);
        return new TableResultResponse<>(result.getTotal(), list);
    }


    public TableResultResponse<User> selectUsersWithRoleId(Query query) {
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<User> list = mapper.selectUsersWithRoleId(query.get("roleId").toString(),
                query.get("name") != null ? query.get("name").toString() : null);
        return new TableResultResponse<>(result.getTotal(), list);
    }

    public TableResultResponse<User> selectUsersWithoutRoleId(Query query) {
        Page<Object> result = PageHelper.startPage(query.getPage(), query.getLimit());
        List<User> list = mapper.selectUsersWithoutRoleId(query.get("roleId").toString(),
                query.get("name") != null ? query.get("name").toString() : null);
        return new TableResultResponse<>(result.getTotal(), list);
    }


}
