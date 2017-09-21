package com.cnpc.admin.dictionary.service;

import com.cnpc.admin.entity.PageData;

import java.util.List;

public interface IDictionaryService {
    /**
     * 根据条件查找，条件为空，查询所有
     * @param pd 条件
     * @return
     * @throws Exception
     */
    List<PageData> find(PageData pd) throws Exception;

    /**
     * 根据id，进行逻辑删除数据字典
     * @param pd
     * @throws Exception
     */
    void deletedById(PageData pd) throws Exception;

    /**
     * 修改
     * @param pd
     * @throws Exception
     */
    void update(PageData pd) throws Exception;

    /**
     * 新增
     * @param pd
     * @throws Exception
     */
    void save(PageData pd) throws Exception;

    /**
     * 唯一编码校验
     * @param pd
     * @return
     */
    List<PageData> findOnlyCode(PageData pd) throws Exception;
}
