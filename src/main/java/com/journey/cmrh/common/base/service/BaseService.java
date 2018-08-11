package com.journey.cmrh.common.base.service;

import com.journey.cmrh.common.base.pojo.Page;

import java.util.List;


/**
 * Service基类
 */
public interface BaseService<T> {

    /**
     * 插入数据
     */
    void insert(T entity);

    /**
     * 批量插入
     */
    void insertBatch(List<T> list);

    /**
     * 删除数据
     */
    void delete(String id);

    /**
     * 批量删除
     */
    void deleteBatch(String[] ids);

    /**
     * 逻辑删除
     */
    void deleteLogic(String id);

    /**
     * 更新数据
     */
    void update(T entity);

    /**
     * 批量更新
     */
    void updateBatch(List<T> list);

    /**
     * 查询所有数据列表
     */
    List<T> findAll();

    /**
     * 查询数据列表，如果需要分页，请设置分页对象，如：model.setPage(new Page<T>());
     */
    List<T> findList(T model);

    /**
     * 查询数据记录
     */
    int getCount(T model);

    /**
     * 获取单条数据
     */
    T get(String id);

    /**
     * 保存数据
     */
    void save(T entity);

    /**
     * 查询分页数据
     */
    Page<T> findPage(T entity);

}
