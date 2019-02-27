package com.mycompany.ejercicio.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.management.MXBean;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class QuerysConfiguration {

    private Map<String, String> querys = new HashMap<>();

    @PostConstruct
    public void loadProperties()
    {
        Properties prop = new Properties();
        String current_dir = System.getProperty("user.dir");
        try
        {
            FileInputStream inputStream = new FileInputStream(current_dir + "\\querys.yml");
            prop.load(inputStream);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some issue finding or loading file....!!! " + e.getMessage());

        }
        for (final Map.Entry<Object, Object> entry : prop.entrySet()) {
            this.querys.put((String) entry.getKey(), (String) entry.getValue());
        }
    }

    public String getQueryByKey(String key){
        return this.querys.get(key);
    }
}
