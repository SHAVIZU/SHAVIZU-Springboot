package com.shavizu.SHAVIZUSpringboot.configuration;

import com.shavizu.SHAVIZUSpringboot.property.AwsS3Properties;
import com.shavizu.SHAVIZUSpringboot.property.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(value = {JwtProperties.class, AwsS3Properties.class})
@Configuration
public class ConfigurationPropertiesConfig {
}
