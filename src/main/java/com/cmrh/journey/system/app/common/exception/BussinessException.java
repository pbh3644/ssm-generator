/**
 * 互联网金融交易系统ifaes (Internet Finance  Assets  Exchange  System)
 * Copyright (c) 2016 信真科技 版权所有
 */
package com.cmrh.journey.system.app.common.exception;

/**
 * @author pangbohuan
 * @description 系统的业务异常（顶级）
 * @date 2018-08-20 14:03
 **/
public class BussinessException extends RuntimeException {
    private static final long serialVersionUID = 538922474277376456L;
    /**
     * JOSN形式返回到页面
     */
    public static final int TYPE_JSON = 1;
    /**
     * 关闭窗
     */
    public static final int TYPE_CLOSE = 2;
    /**
     * 跳转url
     */
    private String url;
    /**
     * 返回错误信息，提示形式
     *
     */
    private int type;
    /**
     * 页面按钮名字
     */
    private String buttonName;

    /**
     * 任意对象
     */
    private transient Object object;

    /**
     * 默认构造方法
     */
    public BussinessException() {
        super();
    }

    /**
     * 含错误信息的构造方法
     *
     * @param message 错误信息
     * @param type 指定返回错误展示方式
     */
    public BussinessException(final String message, final int type) {
        super(message);
        this.type = type;
    }

    /**
     * 含错误信息的构造方法
     *
     * @param msg 错误信息
     * @param exception 运行时异常
     */
    public BussinessException(final String msg, final RuntimeException exception) {
        super(msg, exception);
    }

    /**
     * 含错误信息的构造方法
     *
     * @param message 错误信息
     */
    public BussinessException(final String message) {
        super(message);
    }

    /**
     * 含错误信息、返回地址的构造方法
     *
     * @param message 错误信息
     * @param url 返回地址
     */
    public BussinessException(final String message, final String url) {
        super(message);
        this.url = url;
    }

    /**
     * 含错误信息、返回地址的构造方法
     *
     * @param message 错误信息
     * @param url 返回地址
     * @param buttonName 提示按钮名称
     */
    public BussinessException(final String message, final String url, final String buttonName) {
        super(message);
        this.url = url;
        this.buttonName = buttonName;
    }

    /**
     * 含错误信息、返回地址的构造方法
     * @param message 错误信息
     * @param type 错误类型
     * @param url 返回地址
     * @param buttonName 提示按钮名称
     */
    public BussinessException(final String message, final int type, final String url, final String buttonName) {
        super(message);
        this.type = type;
        this.url = url;
        this.buttonName = buttonName;
    }

    /**
     *
     * @param message 异常信息
     * @param object 需要处理的对象
     */
    public BussinessException(final String message, final Object object) {
        super(message);
        this.object = object;
    }

    /**
     *
     * @param message 提示信息
     * @param url 错误跳转url
     * @param type 错误提示类型
     */
    public BussinessException(final String message, final String url, final int type) {
        super(message);
        this.url = url;
        this.type = type;
    }

    /**
     * 返回错误信息的提示形式
     *
     */
    public int getType() {
        return type;
    }

    /**
     * 获取 页面按钮名字
     */
    public String getButtonName() {
        return buttonName;
    }

    /**
     * 设置 页面按钮名字
     */
    public void setButtonName(final String buttonName) {
        this.buttonName = buttonName;
    }

    /**
     * 获取 跳转url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置 跳转url
     */
    public void setUrl(final String url) {
        this.url = url;
    }

    /**
     * 获取 the object
     *
     * @return
     */
    public Object getObject() {
        return object;
    }

    /**
     * 设置 the object
     *
     * @param
     */
    public void setObject(final Object object) {
        this.object = object;
    }

}
