package com.cnpc.admin.org.service;

import com.cnpc.admin.basic.service.BaseService;
import com.cnpc.admin.entity.PageData;

import java.util.List;

public interface OrgService extends BaseService {

    List<PageData> findOnlyCode(PageData pd) throws Exception;
}
