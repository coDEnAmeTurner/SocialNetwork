/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.configs;

import com.tlqt.filters.CustomAccessDeniedHandler;
import com.tlqt.filters.JwtAuthenticationTokenFilter;
import com.tlqt.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author huu-thanhduong
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.tlqt.controllers",
    "com.tlqt.repositories",
    "com.tlqt.services",
    "com.tlqt.components"
})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests().anyRequest().permitAll();
        http.csrf().ignoringAntMatchers("/api/**");

        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/users/").permitAll();
        http.authorizeRequests().antMatchers("/api/content-types/").permitAll();
        http.authorizeRequests().antMatchers("/api/academic_ranks/").permitAll();
        http.authorizeRequests().antMatchers("/api/degrees/").permitAll();
        http.authorizeRequests().antMatchers("/api/choices/").permitAll();
        http.authorizeRequests().antMatchers("/api/comments/").permitAll();
        http.authorizeRequests().antMatchers("/api/titles/").permitAll();
        

        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/***").access("hasAuthority('admin') or hasAuthority('alumnus') or hasAuthority('lecturer')")
                .antMatchers(HttpMethod.POST, "/api/***").access("hasAuthority('admin') or hasAuthority('alumnus') or hasAuthority('lecturer')")
                .antMatchers(HttpMethod.DELETE, "/api/***").access("hasAuthority('admin') or hasAuthority('alumnus') or hasAuthority('lecturer')")
                .antMatchers(HttpMethod.POST, "/api/emails/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.DELETE, "/api/emails/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.PUT, "/api/invitations/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.PUT, "/api/invitations/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.GET, "/api/posts/count-posts-by-year/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.GET, "/api/posts/count-posts-by-month/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.GET, "/api/posts/count-posts-by-quarter/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.PUT, "/api/questions/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.DELETE, "/api/questions/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.GET, "/api/questions/{questionId}/count-votes/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.GET, "/api/users/emails/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.GET, "/api/users/emails-info/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.PATCH, "/api/users/{userId}/unlock-lecturer/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.PATCH, "/api/users/{userId}/approve-alumnus/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.GET, "/api/users/check-locked/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.GET, "/api/users/count-users-by-year/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.GET, "/api/users/count-users-by-month/").access("hasAuthority('admin')")
                .antMatchers(HttpMethod.GET, "/api/users/count-users-by-quarter/").access("hasAuthority('admin')")
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}
