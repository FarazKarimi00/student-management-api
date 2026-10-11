package com.faraz.studentmanagementapi.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(2)
public class AuthorizationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String role = (String) httpRequest.getAttribute("authenticatedRole");

        String method = httpRequest.getMethod();
        String path = httpRequest.getRequestURI();

        boolean isHardDelete = method.equals("DELETE")
                && path.matches("/api/students/[0-9]+");

        boolean isSoftDelete = method.equals("PATCH")
                && path.matches("/api/students/delete-soft/[0-9]+");

        if((isHardDelete || isSoftDelete) && !"ADMIN".equals(role)) {
            httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                    "{\n" +
                            "    \"success\" : false,\n" +
                            "    \"message\" : \"Access denied. ADMIN role required.\"\n" +
                            "}\n"
            );
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
