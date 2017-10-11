package com.cnpc.ucenter.mapper;

import com.cnpc.ucenter.entity.Org;
import com.cnpc.ucenter.entity.User;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrgMapper extends Mapper<Org> {


    List<Org> getOrgTree(Org org);
}