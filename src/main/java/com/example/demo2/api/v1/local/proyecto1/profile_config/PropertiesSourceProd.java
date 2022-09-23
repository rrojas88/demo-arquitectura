
package com.example.demo2.api.v1.local.proyecto1.profile_config;

import org.springframework.context.annotation.*;


@Configuration
@PropertySource("classpath:prod.properties")
@Profile("prod")
public class PropertiesSourceProd {
    
}
