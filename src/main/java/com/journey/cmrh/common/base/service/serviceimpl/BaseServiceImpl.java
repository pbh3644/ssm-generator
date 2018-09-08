package com.journey.cmrh.common.base.service.serviceimpl;

import com.journey.cmrh.common.base.mapper.BaseMapper;
import com.journey.cmrh.common.base.pojo.BaseEntity;
import com.journey.cmrh.common.base.pojo.Page;
import com.journey.cmrh.common.base.service.BaseService;
import com.journey.cmrh.common.exception.BussinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @Author：pbh
 * @Date：2018-09-08 16:18
 * @Description：ServiceImpl基类
 */
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = BussinessException.class)
public class BaseServiceImpl<D extends BaseMapper<T>, T extends Serializable> implements BaseService<T> {

    /**
     * 数字 ，0
     */
    public static final int INT_ZERO = 0;

    /**
     * 一次批量操作的数据量
     */
    public static final int BATCH_OPERATION_COUNT = 50;

    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    @Override
    public T get(String id) {
        return dao.get(id);
    }

    @Override
    public List<T> findList(T model) {
        return dao.findList(model);
    }

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    @Override
    public int getCount(T model) {
        return dao.getCount(model);
    }

    @Override
    public Page<T> findPage(T entity) {
        Page<T> page = ((BaseEntity) entity).getPage();
        if (page == null) {
            page = new Page<T>();
        }
        page.setRows(dao.findList(entity));
        return page;
    }

    /***************************** 增删改操作 *****************************/

    @Transactional(rollbackFor = BussinessException.class)
    @Override
    public void insert(T entity) {
        ((BaseEntity) entity).preInsert();
        dao.insert(entity);
    }

    @Transactional(rollbackFor = BussinessException.class)
    @Override
    public void insertBatch(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        //批量提交的子列表
        List<T> subList = new ArrayList<T>();
        for (final T t : list) {
            ((BaseEntity) t).preInsert();
            subList.add(t);
            //子列表已满,批量提交
            if (subList.size() == BATCH_OPERATION_COUNT) {
                dao.insertBatch(subList);
                subList = new ArrayList<T>();
            }
        }
        //子列表未满的部分,做一次批量提交
        if (subList.size() > 0 && subList.size() < BATCH_OPERATION_COUNT) {
            dao.insertBatch(subList);
        }
    }

    @Transactional(rollbackFor = BussinessException.class)
    @Override
    public void save(T entity) {
        int result = 0;
        if (((BaseEntity) entity).isNewRecord()) {
            ((BaseEntity) entity).preInsert();
            result = dao.insert(entity);
        } else {
            ((BaseEntity) entity).preUpdate();
            result = dao.update(entity);
        }
        if (result == INT_ZERO) {
            throw new BussinessException("No saved records");
        }
    }

    @Transactional(rollbackFor = BussinessException.class)
    @Override
    public void update(T entity) {
        ((BaseEntity) entity).preUpdate();
        int result = dao.update(entity);
        if (result == INT_ZERO) {
            throw new BussinessException("No updated records");
        }
    }

    @Transactional(rollbackFor = BussinessException.class)
    @Override
    public void updateBatch(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        //批量提交的子列表
        List<T> subList = new ArrayList<T>();
        for (final T t : list) {
            ((BaseEntity) t).preUpdate();
            subList.add(t);
            //子列表已满,批量提交
            if (subList.size() == BATCH_OPERATION_COUNT) {
                int result = dao.updateBatch(subList);
                if (result == INT_ZERO) {
                    throw new BussinessException("No updated records");
                }
                subList = new ArrayList<T>();
            }
        }
        //子列表未满的部分,做一次批量提交
        if (subList.size() > 0 && subList.size() < BATCH_OPERATION_COUNT) {
            int result = dao.updateBatch(subList);
            if (result == INT_ZERO) {
                throw new BussinessException("No updated records");
            }
        }
    }

    @Transactional(rollbackFor = BussinessException.class)
    @Override
    public void delete(final String id) {
        int result = dao.delete(id);
        if (result == INT_ZERO) {
            throw new BussinessException("Record not deleted, id=" + id);
        }
    }

    @Transactional(rollbackFor = BussinessException.class)
    @Override
    public void deleteBatch(final String[] ids) {
        int result = dao.deleteBatch(ids);
        if (result == INT_ZERO) {
            throw new BussinessException("Records not deleted, ids=" + Arrays.toString(ids));
        }
    }

    @Transactional(rollbackFor = BussinessException.class)
    @Override
    public void deleteLogic(final String id) {
        final int result = dao.deleteLogic(id);
        if (result == INT_ZERO) {
            throw new BussinessException("Record not deleted, id=" + id);
        }
    }

}
