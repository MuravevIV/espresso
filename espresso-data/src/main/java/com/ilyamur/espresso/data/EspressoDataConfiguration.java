package com.ilyamur.espresso.data;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan
@EnableMongoRepositories(basePackageClasses = EspressoDataConfiguration.class)
public class EspressoDataConfiguration {
}
