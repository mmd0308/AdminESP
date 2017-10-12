package com.cnpc.admin.mapper;

import com.cnpc.admin.entity.Org;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrgMapper extends Mapper<Org> {


    List<Org> getOrgTree(Org org);
}