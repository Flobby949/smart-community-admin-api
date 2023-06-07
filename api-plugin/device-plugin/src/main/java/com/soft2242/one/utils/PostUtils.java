package com.soft2242.one.utils;

import com.alibaba.fastjson.JSON;
import com.soft2242.one.constant.OpenConstant;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author :Flobby
 * @version :1.0
 * @date :2021/6/26
 * @description :
 */
public class PostUtils {
    /**
     * 发送json
     *
     * @param url
     * @param jsonString
     * @return
     */
    public static String postJson(String url, String jsonString) {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        BufferedReader in = null;
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpPost.setConfig(requestConfig);
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-type", "application/json");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setEntity(new StringEntity(jsonString, StandardCharsets.UTF_8));
            response = httpClient.execute(httpPost);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 发送请求（无返回）
     *
     * @param requestMap
     * @param url
     * @param msg
     * @return
     * @throws Exception
     */
    private static Map doPost(Map<String, Object> requestMap, String url, String msg) throws Exception {
//        checkToken(requestMap);
        Map<String, Object> returnMap = new HashMap<>(3);
        returnMap.put(OpenConstant.CODE, 0);
        try {
            String result = sendPost(url, requestMap);
            Map map = JSON.parseObject(result);
//            if (20017 == Integer.parseInt(String.valueOf(map.get(OpenConstant.CODE)))) {
//                returnMap.put(OpenConstant.MSG, "操作成功");
//            } else
            if (200 == Integer.parseInt(String.valueOf(map.get(OpenConstant.CODE)))) {
                returnMap.put(OpenConstant.MSG, "操作成功");
            } else {
//                log.error(msg + "失败：" + map.get(OpenConstant.MSG));
                returnMap.put(OpenConstant.CODE, Integer.parseInt(String.valueOf(map.get(OpenConstant.CODE))));
                returnMap.put(OpenConstant.MSG, map.get(OpenConstant.MSG));
            }
        } catch (Exception e) {
//            log.error(msg + "失败：" + e.getMessage());
            returnMap.put(OpenConstant.CODE, -1);
            returnMap.put(OpenConstant.MSG, msg + "失败：" + e.getMessage());
        }
        return returnMap;
    }

    /**
     * 发送请求（带返回）
     *
     * @param requestMap
     * @param url
     * @param msg
     * @return
     * @throws Exception
     */
    private static Map doPostAndResult(Map<String, Object> requestMap, String url, String msg) throws Exception {
//        checkToken(requestMap);
        Map<String, Object> returnMap = new HashMap<>(3);
        returnMap.put(OpenConstant.CODE, 0);
        try {
            String result = sendPost(url, requestMap);
            Map map = JSON.parseObject(result);
            if (200 == Integer.parseInt(String.valueOf(map.get(OpenConstant.CODE)))) {
                returnMap.put(OpenConstant.MSG, "操作成功");
                returnMap.put(OpenConstant.DATA, String.valueOf(map.get(OpenConstant.DATA)));
            } else {
//                log.error(msg + "失败：" + map.get(OpenConstant.MSG));
                returnMap.put(OpenConstant.CODE, Integer.parseInt(String.valueOf(map.get(OpenConstant.CODE))));
                returnMap.put(OpenConstant.MSG, map.get(OpenConstant.MSG));
            }
        } catch (Exception e) {
//            log.error(msg + "失败：" + e.getMessage());
            returnMap.put(OpenConstant.CODE, -1);
            returnMap.put(OpenConstant.MSG, msg + "失败：" + e.getMessage());
        }
        return returnMap;
    }


    public static String sendPost(String url, Map<String, Object> paramMap) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        String result = "";
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        // 设置连接主机服务超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)
                // 设置连接请求超时时间
                .setConnectionRequestTimeout(35000)
                // 设置读取数据连接超时时间
                .setSocketTimeout(60000)
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
        // 封装post请求参数
        if (null != paramMap && paramMap.size() > 0) {
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            // 通过map集成entrySet方法获取entity
            Set<Map.Entry<String, Object>> entrySet = paramMap.entrySet();
            // 循环遍历，获取迭代器
            Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> mapEntry = iterator.next();
                nvps.add(new BasicNameValuePair(mapEntry.getKey(), mapEntry.getValue().toString()));
            }

            // 为httpPost设置封装好的请求参数
            try {
                httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        try {
            // httpClient对象执行post请求,并返回响应参数对象
            httpResponse = httpClient.execute(httpPost);
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public static String sendPostByXml(String url, String token, String gatewaySerial, Map<String, Object> paramMap) {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        BufferedReader in = null;
        String result = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpPost.setConfig(requestConfig);
            httpPost.setConfig(requestConfig);
            httpPost.addHeader("Content-Type", "application/xml");
            httpPost.addHeader("EZO-AccessToken", token);
            httpPost.addHeader("EZO-DeviceSerial", gatewaySerial);
            httpPost.setEntity(new StringEntity(JSON.toJSONString(paramMap), Charset.forName("UTF-8")));
            response = httpClient.execute(httpPost);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static String sendDeleteByXml(String url, String token, String gatewaySerial) {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        BufferedReader in = null;
        String result = "";
        try {
            HttpDelete httpDelete = new HttpDelete(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpDelete.setConfig(requestConfig);
            httpDelete.setConfig(requestConfig);
            httpDelete.addHeader("Content-Type", "application/xml");
            httpDelete.addHeader("EZO-AccessToken", token);
            httpDelete.addHeader("EZO-DeviceSerial", gatewaySerial);
            response = httpClient.execute(httpDelete);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String sendGet(String url, String token) {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpClient = null;
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        BufferedReader in = null;
        String result = "";
        try {
            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
            httpGet.setConfig(requestConfig);
            httpGet.setConfig(requestConfig);
            httpGet.addHeader("Content-Type", "application/json");
            httpGet.addHeader("accessToken", token);
            response = httpClient.execute(httpGet);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static String sendPutWithToken(String url, String token, AbstractHttpEntity requestBody) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        String result = "";
        BufferedReader in = null;
        // 创建httpClient实例
        httpClient = HttpClients.createDefault();
        try {
            HttpPut httpPut = new HttpPut(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(30000)
                    .setConnectionRequestTimeout(30000)
                    .setSocketTimeout(30000)
                    .build();
            httpPut.setConfig(requestConfig);
            httpPut.addHeader("Content-type", "application/json");
            httpPut.setHeader("Accept", "application/json");
            httpPut.addHeader("accessToken", token);
            httpPut.setEntity(requestBody);
            response = httpClient.execute(httpPut);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            result = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != response) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

