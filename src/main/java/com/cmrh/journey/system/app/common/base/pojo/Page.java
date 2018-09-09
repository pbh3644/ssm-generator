package com.cmrh.journey.system.app.common.base.pojo;

import com.cmrh.journey.system.app.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * @Author：pbh
 * @Date：2018-09-08 16:18
 * @Description：对分页的基本数据进行一个简单的封装
 */
public class Page<T> implements Serializable {

    private static final long serialVersionUID = -9200442566817527918L;
    public static final int DEFAULTE_XPORTPAGE_SIZE = 5000;

    /**
     * 排序的字段(数据库字段)
     */
    private String sort;

    /**
     * 排序的属性（仅供缓存使用，取自sort）
     */
    private String by;

    /**
     * 升降序方式
     * asc
     * desc
     * 默认为升序排序
     */
    private String order;

    /**
     * 页码，默认是第一页
     */
    private int page = 1;
    /**
     * 每页显示的记录数，默认是15
     */
    private int pageSize = 15;
    /**
     * 总记录数 设置为“-1”表示不查询总
     */
    private int count;
    /**
     * 总页数
     */
    private int totalPage = 1;
    /**
     * 当前页对应的数据中的记录
     */
    private List<T> rows;
    /**
     * 是否执行导出操作
     */
    private boolean export = false;

    /**
     * 当前页码
     *
     * @return int
     */
    public int getPage() {
        return this.page;
    }

    /**
     * 设置当前页码，当前页码为大于等于1的正整数
     */
    public void setPage(int page) {
        //当前页码大于1，对其进行设置，否则一律为默认页码
        if (page >= 1) {
            this.page = page;
        }
    }

    /**
     * 获取 Hibernate FirstResult
     */
    @JsonIgnore
    public int getFirstResult() {
        int firstResult = (getPage() - 1) * getPageSize();
        if (firstResult >= getCount()) {
            firstResult = 0;
        }
        return firstResult;
    }

    /**
     * 每页显示的记录数
     *
     * @return int
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 设置每页显示记录数
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取设置总数
     *
     * @return
     */
    public int getCount() {
        return count;
    }

    /**
     * 设置数据总数
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
        //在设置总记录数的时候计算出对应的总页数
        if (pageSize >= count) {
            this.page = 1;
        }
        this.totalPage = (count % pageSize == 0) ? (count / pageSize) : (count / pageSize + 1);
    }


    /**
     * 总页数
     *
     * @return int
     */
    public int getTotalPage() {
        return this.totalPage;
    }

    /**
     * 返回查询结果集
     *
     * @return java.util.List<T>
     */
    public List<T> getRows() {
        return this.rows;
    }

    /**
     * 设置查询结果集
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    /**
     * 是否有首页
     */
    public boolean hasFirstPage() {
        //当前页码大于1时，表示有首页
        return this.page > 1;
    }

    /**
     * 是否有上一页
     */
    public boolean hasPrevious() {
        //当前页码大于1时，表示有上一页
        return this.page > 1;
    }

    /**
     * 是否有下一页
     */
    public boolean hasNext() {
        //当前页码小于总页数时，表示有下一页
        return this.page < this.totalPage;
    }

    /**
     * 是否有尾页
     */
    public boolean hasEndPage() {
        //当前页码小于总页数时，表示有尾页
        return this.page < this.totalPage;
    }

    /**
     * 上一页页码
     */
    public int prevPageNo() {
        return this.page - 1;
    }

    /**
     * 下一页页码
     */
    public int nextPageNo() {
        return this.page + 1;
    }

    @JsonIgnore
    public String getSort() {
        return this.sort;
    }

    @JsonIgnore
    public String getBy() {
        return this.by;
    }

    public void setSort(String sort) {
        //排序的属性，仅供缓存操作使用
        this.by = sort;
        this.sort = StringUtil.toUnderScoreCase(sort);
    }

    @JsonIgnore
    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Page [pageNo=").append(page).append(", pageSize=")
                .append(pageSize).append(", rows=").append(rows)
                .append(", totalPage=").append(totalPage)
                .append(", count=").append(count).append("]");
        return builder.toString();
    }

    @JsonIgnore
    public boolean isExport() {
        return export;
    }

    public void setExport(boolean export) {
        this.export = export;
    }


}
