package com.cnpc.admin.organization.service;

import com.cnpc.admin.entity.PageData;

import java.util.List;

public interface IAdminOrgService   {
    List<PageData> find(PageData pd) throws Exception;

    void save(PageData pd) throws Exception;

    void update(PageData pd) throws Exception;

    void deletedById(PageData pd) throws Exception;

    List<PageData> findOnlyCode(PageData pd) throws Exception;
}
