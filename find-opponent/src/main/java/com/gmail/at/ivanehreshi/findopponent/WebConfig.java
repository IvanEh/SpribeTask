package com.gmail.at.ivanehreshi.findopponent;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.gmail.at.ivanehreshi.findopponent.controller")
public class WebConfig extends WebMvcConfigurerAdapter {
}
