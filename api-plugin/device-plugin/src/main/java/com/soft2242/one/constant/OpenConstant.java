package com.soft2242.one.constant;

/**
 * @author :Flobby
 * @version :1.0
 * @date :2021/6/26
 * @description :
 */

public class OpenConstant {
    final public static String CODE = "200";
    final public static String MSG = "操作成功";
    public static final String ERROR_MSG = "token过期";
    final public static String DATA = null;
    public static String OPEN_ACCESS_TOKEN;
    public static Long OPEN_EXPIRE_TIME;
    /**
     * 萤石云AK
     */
    final public static String APP_KEY = "7aa4151eca1840369a32feb23b764250";
    /**
     * 萤石云AS
     */
    final public static String APP_SECRET = "e25c18ebdd1612119565a16a3ca653ca";
    /**
     * 萤石云开发平台URL
     */
    final public static String YS_OPEN_URL = "https://open.ys7.com/api/lapp";
    /**
     * 萤石云开发平台设备操作URL
     */
    final public static String YS_DEVICE_URL = "https://open.ys7.com/api/v3";
    /**
     * 开放平台获取用户TOKEN
     */
    final public static String GET_OPEN_ACCESS_TOKEN = "/token/get";
    /**
     * 校验是否支持协议
     */
    final public static String DEVICE_IS_ENABLE = "/device/support/ezviz";
    /**
     * 添加设备
     */
    final public static String ADD_DEVICE = "/device/add";
    /**
     * 删除设备
     */
    final public static String DELETE_DEVICE = "/device/delete";
    /**
     * 修改设备名称
     */
    final public static String UPDATE_DEVICE = "/device/name/update";
    /**
     * 设备基础信息查询
     */
    final public static String DEVICE_INFO = "/device/searchDeviceInfo";
    /**
     * 设备列表
     */
    final public static String DEVICE_LIST = "/device/list";
    /**
     * 插座
     */
    final public static String OUTLET_SWITCH = "/Outlet/0/OutletControl/OutletSwitch";
    /**
     * 监控视频列表
     */
    final public static String CAMERA_LIST = "/camera/list";
    /**
     * 获取视频播放地址
     */
    final public static String GET_LIVE_ADDRESS = "/v2/live/address/get";
}
