package com.cnpc.admin.log.service;

import com.cnpc.admin.entity.PageData;

import java.util.List;

public interface ILogService {
    List<PageData> findAll() throws Exception;
}
