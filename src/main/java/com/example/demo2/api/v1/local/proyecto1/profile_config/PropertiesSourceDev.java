
package com.example.demo2.api.v1.local.proyecto1.profile_config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;


@Configuration
@PropertySource("classpath:dev.properties")
@Profile("dev")
public class PropertiesSourceDev {
    
}
