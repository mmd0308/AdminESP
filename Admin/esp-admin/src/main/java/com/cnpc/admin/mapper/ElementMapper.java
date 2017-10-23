package com.cnpc.admin.mapper;

import com.cnpc.admin.entity.Element;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ElementMapper extends Mapper<Element> {

    List<Element> selectElementsByUserId(@Param("userId") String userId);

    List<Element> selectElementsByUserIdAndMenuId(@Param("userId") String userId,@Param("menuId") String menuId);
}