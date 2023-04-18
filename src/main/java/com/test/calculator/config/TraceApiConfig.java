package com.test.calculator.config;

import io.corp.calculator.TracerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TraceApiConfig {

  @Bean
  TracerImpl tracerAPI() {
    return new TracerImpl();
  }

}
