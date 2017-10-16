package com.cnpc.admin.mapper;

import com.cnpc.admin.entity.Menu;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface MenuMapper extends Mapper<Menu> {

    public String selectMaxLevelCodeByParentId(@Param("parentId") String parentId);
}