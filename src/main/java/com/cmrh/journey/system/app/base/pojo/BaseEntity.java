package com.cmrh.journey.system.app.base.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;


/**
 * @Author：pbh
 * @Date：2018-09-08 16:18
 * @Description：pojo基类
 */
public abstract class BaseEntity<T> implements Serializable {


    private static final long serialVersionUID = 1L;


    /**
     * 创建人ID
     */
    private int sAddUserId;

    /**
     * 增加时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date sAddTime;


    /**
     * 修改人ID
     */
    private int sUpdateUserId;

    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date sUpdateTime;

    /**
     * 删除人ID
     */
    private int sDeleteUserId;

    /**
     * 删除时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date sDeleteTime;

    /**
     * 状态0:正常,1:删除
     */
    private byte sDeleteFlag;

    /**
     * 备注
     */
    private String sRemark;


    /**
     * 主键:UUID
     */
    protected String uuid;

    /**
     * 当前实体分页对象
     */
    protected Page<T> page;

    /**
     * 是否是新记录（默认：false），调用setNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     *
     * @return
     */
    @JsonIgnore
    protected boolean newRecord;

    /**
     * 关键字
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String keywords;

    /**
     * 日期类型  "":全部，01：近7天，02：近1个月，03：近3个月，04：近一年
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String dateType;
    /**
     * 开始日期
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String startTime;
    /**
     * 结束日期
     **/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String endTime;

    /****************************导出相关属性****************************/
    /**
     * 导出标题
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String exportTitle;
    /**
     * 导出列显示
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String[] hearders;
    /**
     * 导出列属性
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String[] fields;

    /**
     * 空构造函数
     */
    public BaseEntity() {
        super();
    }

    /**
     * 构造函数
     *
     * @param uuid
     */
    public BaseEntity(final String uuid) {
        super();
        this.uuid = StringUtils.isEmpty(uuid) ? genUuid() : uuid;
    }

    /**
     * 获得uuid
     *
     * @return
     */
    @JsonProperty("id")
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置uuid
     *
     * @param uuid
     */
    public void setUuid(final String uuid) {
        this.uuid = uuid;
    }


    /**
     * 是否是新记录（默认：false），调用setNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     *
     * @return
     */
    @JsonIgnore
    public boolean isNewRecord() {
        return newRecord || isIdBlank();
    }

    /**
     * 判断uuid是否为空
     *
     * @return
     */
    private boolean isIdBlank() {
        return StringUtils.isBlank(uuid);
    }

    /**
     * 是否是新记录（默认：false），调用setNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     */
    public void setNewRecord(final boolean newRecord) {
        this.newRecord = newRecord;
    }

    /**
     * 获得当前实体分页对象
     *
     * @return
     */
    @JsonIgnore
    @XmlTransient
    public Page<T> getPage() {
        return page;
    }

    /**
     * 设置当前实体分页对象
     *
     * @param page
     * @return
     */
    public Page<T> setPage(final Page<T> page) {
        this.page = page;
        return page;
    }

    /**
     * 保存数据库前预处理
     */
    public void preInsert() {
        this.uuid = StringUtils.isBlank(this.uuid) ? genUuid() : this.uuid;
    }

    /**
     * 保存数据库前预更新
     */
    public void preUpdate() {
    }

    /**
     * 获得关键字
     *
     * @return
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置关键字
     *
     * @param keywords
     */
    public void setKeywords(final String keywords) {
        this.keywords = StringUtils.isBlank(keywords) ? null : keywords.trim();
    }

    /**
     * excel导出标题
     *
     * @return
     */
    public String getExportTitle() {
        return exportTitle;
    }

    /**
     * 设置excel导出标题
     */
    public void setExportTitle(final String exportTitle) {
        this.exportTitle = exportTitle;
    }

    /**
     * 获得"导出列显示"
     *
     * @return
     */
    public String[] getHearders() {
        return hearders;
    }

    /**
     * 设置导出列显示
     *
     * @param hearders
     */
    public void setHearders(final String hearders) {
        this.hearders = StringUtils.isBlank(hearders) ? null : hearders.split(",");
    }

    /**
     * 获得导出列属性
     *
     * @return
     */
    public String[] getFields() {
        return fields;
    }

    /**
     * 导出列属性
     *
     * @param fields
     */
    public void setFields(final String fields) {
        this.fields = StringUtils.isBlank(fields) ? null : fields.split(",");
    }


    /**
     * 设置日期类型
     *
     * @param dateType
     */
    public void setDateType(final String dateType) {
        this.dateType = dateType;
    }


    /**
     * 设置开始时间
     *
     * @param startTime
     */
    public void setStartTime(final String startTime) {
        this.startTime = startTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime
     */
    public void setEndTime(final String endTime) {
        this.endTime = endTime;
    }


    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String genUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public Date currentTime() {
        return new Date();
    }


    @Override
    public String toString() {
        return "BaseEntity{" +
                ", uuid='" + uuid + '\'' +
                ", page=" + page +
                ", newRecord=" + newRecord +
                ", keywords='" + keywords + '\'' +
                ", dateType='" + dateType + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", exportTitle='" + exportTitle + '\'' +
                ", hearders=" + Arrays.toString(hearders) +
                ", fields=" + Arrays.toString(fields) +
                '}';
    }
}