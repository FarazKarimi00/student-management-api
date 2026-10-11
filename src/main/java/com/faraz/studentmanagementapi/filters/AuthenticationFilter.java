package com.faraz.studentmanagementapi.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String apiKey = httpRequest.getHeader("x-api-key");

        String role;

        if("user-12345".equals(apiKey)) {
            role = "USER";
        } else if ("admin-67890".equals(apiKey)) {
            role = "ADMIN";
        } else {
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                    "{\n" +
                            "    \"success\" : false,\n" +
                            "    \"message\" : \"Invalid or missing api key\"\n" +
                            "}"
            );
            return;
        }
        httpRequest.setAttribute("authenticatedRole",role);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
