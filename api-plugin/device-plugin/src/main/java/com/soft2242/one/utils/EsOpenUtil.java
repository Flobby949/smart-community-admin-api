package com.soft2242.one.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.soft2242.one.constant.OpenConstant;
import com.soft2242.one.entity.BooleanEntity;
import com.soft2242.one.entity.DeviceResponse;
import com.soft2242.one.entity.OpenAccessToken;
import com.soft2242.one.entity.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Flobby
 * @description Saas平台设备操作工具类
 */
@Slf4j
public class EsOpenUtil {
    public static void main(String[] args) {
        // System.out.println(checkToken());
        // searchDeviceInfo("Q01866315");
        // checkDeviceEnable(OpenConstant.APP_KEY, "CS-T30-10A", "V1.2.1 build 191106");
        // addDevice("Q01866315", "WJBTLN");
        // deleteDevice("Q01866315");
        // updateDevice("Q01866315", "Flobby");
        // getSocketList();
        // getCameraList();
        System.out.println(getVideoUrl("E95915602"));
        // changeSocketEnable("Q01866315", 0);
        // getSocketEnable("Q01866315");
    }

    static final String DEVICE_URL_PREFIX = "/otap/prop/";

    /**
     * 获取Token
     */
    public static void getYsToken() {
        String getTokenUrl = OpenConstant.YS_OPEN_URL + OpenConstant.GET_OPEN_ACCESS_TOKEN;
        Map<String, Object> map = new HashMap<>(2);
        map.put("appKey", OpenConstant.APP_KEY);
        map.put("appSecret", OpenConstant.APP_SECRET);
        String response = PostUtils.sendPost(getTokenUrl, map);
        log.info(response);
        JSONObject jsonObject = JSON.parseObject(response);
        if ("200".equals(jsonObject.getString("code"))) {
            OpenAccessToken data = jsonObject.getObject("data", OpenAccessToken.class);
            OpenConstant.OPEN_ACCESS_TOKEN = data.getAccessToken();
            OpenConstant.OPEN_EXPIRE_TIME = System.currentTimeMillis() + 86400;
        } else {
            log.error("Token 获取失败");
        }
    }

    /**
     * 校验token是否过期
     *
     * @return token
     */
    public static String checkToken() {
        boolean tokenExist = StringUtils.isNotBlank(OpenConstant.OPEN_ACCESS_TOKEN);
        long now = System.currentTimeMillis();
        if (!tokenExist) {
            log.error(OpenConstant.ERROR_MSG);
            getYsToken();
        }
        // 时间戳不存在
        if (OpenConstant.OPEN_EXPIRE_TIME == null) {
            log.error(OpenConstant.ERROR_MSG);
            getYsToken();
        }
        // token过期
        if (now > OpenConstant.OPEN_EXPIRE_TIME) {
            log.error(OpenConstant.ERROR_MSG);
            getYsToken();
        }
        return OpenConstant.OPEN_ACCESS_TOKEN;
    }

    public static void searchDeviceInfo(String deviceSerial) {
        String getSearchUrl = OpenConstant.YS_DEVICE_URL + OpenConstant.DEVICE_INFO;
        Map<String, Object> map = new HashMap<>(3);
        map.put("accessToken", checkToken());
        map.put("deviceSerial", deviceSerial);
        String response = PostUtils.sendPost(getSearchUrl, map);
        log.info(response);
    }

    public static void checkDeviceEnable(String appKey, String model, String version) {
        String getSearchUrl = OpenConstant.YS_OPEN_URL + OpenConstant.DEVICE_IS_ENABLE;
        Map<String, Object> map = new HashMap<>(3);
        map.put("accessToken", checkToken());
        map.put("appKey", appKey);
        map.put("model", model);
        map.put("version", version);
        String response = PostUtils.sendPost(getSearchUrl, map);
        log.info(response);
    }

    public static void getCameraList() {
        String getSearchUrl = OpenConstant.YS_OPEN_URL + OpenConstant.CAMERA_LIST;
        Map<String, Object> map = new HashMap<>(3);
        map.put("accessToken", checkToken());
        String response = PostUtils.sendPost(getSearchUrl, map);
        log.info(response);
    }

    public static ResponseBody getVideoUrl(String serialNumber) {
        String getSearchUrl = OpenConstant.YS_OPEN_URL + OpenConstant.GET_LIVE_ADDRESS;
        Map<String, Object> map = new HashMap<>(3);
        map.put("accessToken", checkToken());
        map.put("deviceSerial", serialNumber);
        map.put("protocol", 2);
        String response = PostUtils.sendPost(getSearchUrl, map);
        return JSON.parseObject(response, ResponseBody.class);
    }

    /**
     * 添加设备
     *
     * @param serialNumber 设备序列号
     * @param validateCode 设备验证码
     * @return string
     */
    public static String addDevice(String serialNumber, String validateCode) {
        String getAddUrl = OpenConstant.YS_OPEN_URL + OpenConstant.ADD_DEVICE;
        Map<String, Object> map = new HashMap<>(3);
        map.put("accessToken", checkToken());
        map.put("deviceSerial", serialNumber);
        map.put("validateCode", validateCode);
        String response = PostUtils.sendPost(getAddUrl, map);
        log.info("添加设备: " + response);
        return JSON.parseObject(response).getString("code");
    }

    /**
     * 修改设备名称
     *
     * @param serialNumber 序列号
     * @param deviceName   name
     * @return string
     */
    public static String updateDevice(String serialNumber, String deviceName) {
        String updateNameUrl = OpenConstant.YS_OPEN_URL + OpenConstant.UPDATE_DEVICE;
        Map<String, Object> map = new HashMap<>(3);
        map.put("accessToken", checkToken());
        map.put("deviceSerial", serialNumber);
        map.put("deviceName", deviceName);
        String response = PostUtils.sendPost(updateNameUrl, map);
        log.info("修改设备:" + response);
        return JSON.parseObject(response).getString("code");
    }

    /**
     * 删除设备
     *
     * @param serialNumber 设备序列号
     * @return string
     */
    public static String deleteDevice(String serialNumber) {
        String getDeleteUrl = OpenConstant.YS_OPEN_URL + OpenConstant.DELETE_DEVICE;
        Map<String, Object> map = new HashMap<>(2);
        map.put("accessToken", checkToken());
        map.put("deviceSerial", serialNumber);
        String response = PostUtils.sendPost(getDeleteUrl, map);
        log.info("删除设备" + response);
        return JSON.parseObject(response).getString("code");
    }

    /**
     * 获取插座列表
     *
     * @param pageNo   页码 从0开始
     * @param pageSize 每页的大小 默认为10，最大为50
     * @return List
     */
    public static List<DeviceResponse> getSocketList(Integer pageNo, Integer pageSize) {
        String getSocketListUrl = OpenConstant.YS_OPEN_URL + OpenConstant.DEVICE_LIST;
        Map<String, Object> map = new HashMap<>(4);
        map.put("accessToken", checkToken());
        map.put("pageNo", pageNo);
        map.put("pageSize", pageSize);
        String response = PostUtils.sendPost(getSocketListUrl, map);
        log.info("设备列表: " + response);
        String arrayString = JSON.parseObject(response).getString("data");
        return JSONObject.parseArray(arrayString, DeviceResponse.class);
    }

    /**
     * 获取插座列表
     *
     * @return List
     */
    public static List<DeviceResponse> getSocketList() {
        return getSocketList(0, 10);
    }

    /**
     * 获取插座状态
     *
     * @param deviceSerial 序列号
     * @return string
     */
    public static String getSocketEnable(String deviceSerial) {
        String changeEnableUrl = OpenConstant.YS_DEVICE_URL + DEVICE_URL_PREFIX + deviceSerial + OpenConstant.OUTLET_SWITCH;
        String response = PostUtils.sendGet(changeEnableUrl, checkToken());
        log.info("开关状态" + response);
        return null;
    }

    /**
     * TODO 修改插座状态
     *
     * @param deviceSerial 序列号
     * @param cmd          开关
     * @return string
     */
    public static String changeSocketEnable(String deviceSerial, Integer cmd) {
        String changeEnableUrl = OpenConstant.YS_DEVICE_URL + DEVICE_URL_PREFIX + deviceSerial + OpenConstant.OUTLET_SWITCH;
        String response = PostUtils.sendPutWithToken(changeEnableUrl, checkToken(), new BooleanEntity(cmd));
        log.info("开/关" + response);
        // return JSON.parseObject(response).getString("code");
        return null;
    }
}
