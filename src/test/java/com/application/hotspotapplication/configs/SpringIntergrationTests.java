package com.application.hotspotapplication.configs;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@CucumberContextConfiguration
@SpringBootTest
@EnableWebMvc
@ActiveProfiles("test")
public class SpringIntergrationTests {
}
