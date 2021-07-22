package com.tansun.handler;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 验证码
 * 过滤器依赖于servlet
 */
public class MyFilters extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse, javax.servlet.FilterChain filterChain) throws ServletException, IOException {
        String servletPath = httpServletRequest.getServletPath();
        // 获取验证码
        if (servletPath.matches("/captcha.jpg")) {
            httpServletResponse.setContentType("image/jpeg");
            //禁止图像缓存。
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setHeader("Cache-Control", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            // 参数：宽、高、字符数、干扰量
//            CaptchaBean
        }

    }

    private void getCookie(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.equals("")) {
                    // todo
                }
            }
        }
    }

}
