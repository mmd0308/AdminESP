package com.cnpc.admin.mapper;

import com.cnpc.admin.entity.Dict;
import com.cnpc.admin.entity.Org;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface DictMapper extends Mapper<Dict> {


    List<Map> getDictByPIDToMap(String pid);

    String getLevelCodeByParentId(String parentId);

    List<Map> checkCode(Dict org);
}