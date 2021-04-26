package com.application.hotspotapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class HotspotApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(HotspotApplication.class, args);
  }

}

