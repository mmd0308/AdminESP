package com.cnpc.admin.mapper;

import com.cnpc.admin.entity.Org;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface OrgMapper extends Mapper<Org> {


    List<Map> getOrgByPIDToMap(String pid);
}