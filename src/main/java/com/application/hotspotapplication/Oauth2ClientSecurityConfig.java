package com.application.hotspotapplication;

import com.application.hotspotapplication.entity.Users;
import com.application.hotspotapplication.service.UsersService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Configuration
@EnableWebSecurity
public class Oauth2ClientSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**").authorizeRequests()
                .antMatchers(new String[]{"/", "/login","/user/all"}).permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                    .userInfoEndpoint()
                .and()
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {


                        DefaultOAuth2User user= (DefaultOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        String email = user.getAttribute("email");
                        String name = user.getAttribute("name");

                        UsersService service = new UsersService();
                        service.createUser(email,name,"Mohlokoane");

                        response.sendRedirect("/list");
                    }
                });
    }
}
