package com.wzebra.cjudge.config;

import com.wzebra.cjudge.algorithm.encrypt.Encipher;
import com.wzebra.cjudge.algorithm.encrypt.EncryptInfo;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

@Order(1)
@WebFilter(urlPatterns = "/api/*")
public class AuthConfig implements Filter {
    static HashSet<String> safePath;

    static {
        safePath = new HashSet<>();
        safePath.add(PathConfig.path + "/login");
        safePath.add(PathConfig.path + "/register");
        safePath.add(PathConfig.path + "/local/judge");
        safePath.add(PathConfig.path + "/getUser/false");

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = ((HttpServletResponse) servletResponse);
        HttpServletRequest request = ((HttpServletRequest) servletRequest);

        //跨域配置，禁止注释即可
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Origin","http://localhost:8080");

        String url = request.getRequestURI();
        if (safePath.contains(url)) { //Path that don't need auth
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        } else {
            if (!Encipher.getFromCookie(request.getCookies()).isExpired()) {
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }
        response.setStatus(401);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }


}
