package com.chenyue.blog.conf.jwt.interceptor;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.chenyue.blog.conf.jwt.JwtUtils;
import org.springframework.util.Assert;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenyue7@foxmail.com
 * @date 2022/2/18
 * @description:
 */
public class TokenAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        Assert.notNull(token, "token can't be null");
        DecodedJWT verifiedToken = JwtUtils.verify(token);
        String userId = verifiedToken.getClaim("userId").asString();
        request.setAttribute("userId", userId);
        return true;
    }
}
