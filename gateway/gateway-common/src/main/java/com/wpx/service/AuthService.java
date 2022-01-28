package com.wpx.service;

import com.wpx.auth.base.AuthMsg;
import com.wpx.auth.base.AuthResult;
import org.springframework.http.HttpHeaders;

/**
 * @author 不会飞的小鹏
 * 授权鉴权服务
 */
public interface AuthService {

    /**
     * 获取header中的权限字符串
     *
     * @param headers 请求头
     * @return 权限字符串
     */
    String getAuthentication(HttpHeaders headers);

    /**
     * 检查token是否正确
     *
     * @param authentication 包含token的字符串
     * @param target         返回的AuthResult类型
     * @return Result
     */
    <T extends AuthMsg> AuthResult<T> checkToken(String authentication, Class<T> target);

}
