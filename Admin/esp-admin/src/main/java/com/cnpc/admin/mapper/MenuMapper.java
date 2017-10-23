package com.cnpc.admin.mapper;

import com.cnpc.admin.entity.Menu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface MenuMapper extends Mapper<Menu> {

    String selectMaxLevelCodeByParentId(@Param("parentId") String parentId);

    List<Menu> selectMenusByUserId(@Param("userId") String userId);
}