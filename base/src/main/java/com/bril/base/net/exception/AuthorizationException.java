package com.bril.base.net.exception;

/**
 * 认证授权失败
 */

public class AuthorizationException extends Exception {
    public AuthorizationException() {
        super("Authorization 用户认证异常");
    }
}
