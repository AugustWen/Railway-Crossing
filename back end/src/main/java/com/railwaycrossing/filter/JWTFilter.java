package com.railwaycrossing.filter;


import com.railwaycrossing.config.shiro.JWTToken;
import org.apache.log4j.Logger;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;


public class JWTFilter extends BasicHttpAuthenticationFilter {

    private Logger logger = Logger.getLogger(JWTFilter.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        // 检验Request的Header中是否携带Token
        if (isLoginAttempt(request, response)) {

            // 若携带Token, 则执行登入, 检查Token是否正确
            try {

                executeLogin(request, response);

                return true;

            } catch (Exception e) {

                // token错误
                responseError(response, e.getMessage());

            }

        }

        // 不存在token, 无需检查, 直接返回true, 执行登入操作
        return true;

    }

/*
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpResponse.setHeader("Access-control-Allow-Origin", httpRequest.getHeader("Origin"));
            httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod());
            httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
            httpResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
*/


    /**
     * 检测 Header 中是否携带Token
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {

        HttpServletRequest req = (HttpServletRequest) request;

        String token = req.getHeader("Token");

        boolean real = (token != null);

        return real;

    }


    /**
     * 执行登录
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String token = httpRequest.getHeader("Token");

        JWTToken jwtToken = new JWTToken(token);

        Subject currentUser = getSubject(request, response);

        // 执行认证登录
        currentUser.login(jwtToken);

        return true;

    }


    /**
     * 非法请求跳转到/unauthorized/
     * @param response
     * @param message
     */
    private void responseError(ServletResponse response, String message) {

        try {

            HttpServletResponse httpServletResponse = (HttpServletResponse) response;

            message = URLEncoder.encode(message, "UTF-8");

            httpServletResponse.sendRedirect("/unauthorized/" + message);

        } catch (IOException e) {

            e.printStackTrace();

            logger.error(e.getMessage());

        }

    }

}