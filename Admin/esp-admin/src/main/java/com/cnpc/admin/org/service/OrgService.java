package com.cnpc.admin.org.service;

import com.cnpc.admin.basic.service.IBaseService;
import com.cnpc.admin.entity.PageData;

import java.util.List;

public interface OrgService extends IBaseService {

    List<PageData> findOnlyCode(PageData pd) throws Exception;
}
