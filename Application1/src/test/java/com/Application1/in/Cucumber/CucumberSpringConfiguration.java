package com.Application1.in.Cucumber;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.Application1.in.Application1Application;

import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes=Application1Application.class)
public class CucumberSpringConfiguration {
}
