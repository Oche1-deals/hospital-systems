/**
 * Created By: Innocent Idoko
 * Time:07:18
 */
package com.Hospital_mang.system.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import org.slf4j.LoggerFactory;

@Component
public class ApiLoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(ApiLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // Log the request URL
        logger.info("API Call: {}", request.getRequestURL().toString()+" request=> "+request.getRemoteHost());
        return true; // Proceed with the next interceptor or handler
    }
}
