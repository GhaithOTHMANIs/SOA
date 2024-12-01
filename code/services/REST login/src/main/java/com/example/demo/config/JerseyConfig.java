package com.example.demo.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.example.demo.services.Service;



@Configuration
public class JerseyConfig extends ResourceConfig{
	public JerseyConfig() {
        register(Service.class);
    }
}
