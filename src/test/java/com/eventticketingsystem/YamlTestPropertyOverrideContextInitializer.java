package com.eventticketingsystem;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.Resource;

public class YamlTestPropertyOverrideContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        try {
            Resource resource = applicationContext.getResource("classpath:application-test.yml");
            YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
            yaml.setResources(resource);
            PropertiesPropertySource propertySource = new PropertiesPropertySource("testProperties", yaml.getObject());
            ConfigurableEnvironment environment = applicationContext.getEnvironment();
            MutablePropertySources propertySources = environment.getPropertySources();
            propertySources.addFirst(propertySource);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
