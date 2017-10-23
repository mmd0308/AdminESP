package com.cnpc.admin.mapper;

import com.cnpc.admin.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    List<User> selectUsersWithRoleId(@Param("roleId") String roleId, @Param("name") String name);

    List<User> selectUsersWithoutRoleId(@Param("roleId") String roleId, @Param("name") String name);


}