package com.soft2242.one.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author : Flobby
 * @program : community-client-api
 * @description :
 * @create : 2023-06-07 13:21
 **/

@Data
public class ResponseBody {
    private String code;
    private String msg;
    private JSONObject data;
}
