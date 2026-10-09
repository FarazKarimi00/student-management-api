package com.faraz.studentmanagementapi.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        long start = System.currentTimeMillis();

        System.out.println("Request: " +
                httpRequest.getMethod() +
                " " + httpRequest.getRequestURI());

        filterChain.doFilter(servletRequest, servletResponse);

        long timeTaken = System.currentTimeMillis() - start;

        System.out.println("Response: " +
                httpResponse.getStatus() +
                " | Time: " + timeTaken + "ms"
        );
    }
}
