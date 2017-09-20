package com.cnpc.admin.organization.service;

import com.cnpc.admin.entity.PageData;

import java.util.List;

public interface IAdminOrgService   {
    List<PageData> findAll() throws Exception;
}
