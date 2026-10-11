package com.faraz.studentmanagementapi.config;

import com.faraz.studentmanagementapi.interceptors.LoggingInterceptor;
import com.faraz.studentmanagementapi.interceptors.PerformanceInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final LoggingInterceptor loggingInterceptor;
    private final PerformanceInterceptor performanceInterceptor;

    public WebConfig(LoggingInterceptor loggingInterceptor,
                     PerformanceInterceptor performanceInterceptor) {
        this.loggingInterceptor = loggingInterceptor;
        this.performanceInterceptor = performanceInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/api/**")
                .order(1);
        registry.addInterceptor(performanceInterceptor)
                .addPathPatterns("/api/**")
                .order(2);
    }
}
