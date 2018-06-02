package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author brendanclemenzi
 */
@SpringBootApplication
@RestController
@EnableAutoConfiguration
public class ExampleApplication
{
    @RequestMapping("/")
    String hello()
    {
        return "Hello World";
    }

    public static void main(String[] args)
    {
        System.out.println("Starting ExampleApplication");
        
        SpringApplication.run(ExampleApplication.class, args);
    }
}
