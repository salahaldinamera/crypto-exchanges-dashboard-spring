package com.crypto.dashboardweb.controller.configs;

import com.crypto.dashboardweb.controller.utils.RequestsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Web Config
 * Adds the interceptor and the cors mapping
 * @see RequestsInterceptor
 */
@Configuration
@EnableWebMvc
@EnableScheduling
@EnableCaching
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RequestsInterceptor requestsInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestsInterceptor);
    }
}
