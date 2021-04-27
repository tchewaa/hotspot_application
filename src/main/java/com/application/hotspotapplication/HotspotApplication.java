package com.application.hotspotapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
public class HotspotApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(HotspotApplication.class, args);
  }


  @Configuration
  @EnableWebSecurity
  class Oauth2ClientSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
      http.csrf().disable()
              .addFilterAfter(new AccessTokenFilter(), UsernamePasswordAuthenticationFilter.class)
              .antMatcher("/**").authorizeRequests()
              .antMatchers(new String[]{"/", "/login","/user/all"}).permitAll()
              .anyRequest().authenticated();

    }
  }
}

