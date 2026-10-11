package com.faraz.studentmanagementapi.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PerformanceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        long startTime = System.nanoTime();
        request.setAttribute("startTime", startTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        Long startTime = (Long) request.getAttribute("startTime");
        if(startTime != null) {
            long duration = System.nanoTime() - startTime;
            System.out.println("Request execution time: " + duration / 1000000.0 + "ms");
        }
    }
}
