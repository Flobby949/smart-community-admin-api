package com.soft2242.one.base.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 错误编码
 *
 * @author ao&dl
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNAUTHORIZED(401, "还未授权，不能访问"),
    FORBIDDEN(403, "没有权限，禁止访问"),
    DISABLED(600,"该用户状态已被禁用,禁止登陆。请联系管理员处理!"),
    INTERNAL_SERVER_ERROR(500, "服务器异常，请稍后再试");

    private final int code;
    private final String msg;
}
