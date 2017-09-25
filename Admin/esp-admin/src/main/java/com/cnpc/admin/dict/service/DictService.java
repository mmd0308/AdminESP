package com.cnpc.admin.dict.service;

import com.cnpc.admin.basic.service.BaseService;
import com.cnpc.admin.entity.PageData;

import java.util.List;

public interface DictService extends BaseService {
    /**
     * 唯一编码校验
     * @param pd
     * @return
     */
    List<PageData> findOnlyCode(PageData pd) throws Exception;
}
