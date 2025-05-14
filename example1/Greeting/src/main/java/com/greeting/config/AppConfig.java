package com.greeting.config;

import com.greeting.controller.GreetingController;
import com.greeting.service.GreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public GreetingController greetingController() {
        return new GreetingController();
    }

    @Bean
    public GreetingService greetingService() {
        return new GreetingService();
    }
}
