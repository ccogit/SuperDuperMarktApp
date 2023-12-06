package com.brockhaus.mainapp.config;


import com.brockhaus.mainapp.creators.KaeseCreator;
import com.brockhaus.mainapp.creators.WeinCreator;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configs {
    @Bean
    public Faker faker() {
        return new Faker();
    }

    @Bean
    public WeinCreator weinCreator() {
        return new WeinCreator();
    }

    @Bean
    public KaeseCreator kaeseCreator() {
        return new KaeseCreator();
    }

}
