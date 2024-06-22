/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.tlqt.filters.CustomAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author Admin
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.tlqt.controllers",
    "com.tlqt.repositories",
    "com.tlqt.services"
})
@Order(2)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
//    com.cloudinary.utils.ObjectUtils;
    public Cloudinary cloudinary() {
        Cloudinary cloudinary
                = new Cloudinary(ObjectUtils.asMap(
                        "cloud_name", "dymtveeni",
                        "api_key", "117826738689585",
                        "api_secret", "IW8hKIZcDy9-uvLEpM6XSsA708U",
                        "secure", true));
        return cloudinary;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        //su dung trang login mac dinh
        http.formLogin()
                .usernameParameter("username")
                .passwordParameter("password");

        http.formLogin().defaultSuccessUrl("/")
                .failureUrl("/login?error");
        http.logout().logoutSuccessUrl("/login");

        http.exceptionHandling()
                .accessDeniedPage("/login?accessDenied");

        http.authorizeRequests()
                .antMatchers("/login*").permitAll()
                .antMatchers("/logout").permitAll()
                .antMatchers("/**").hasAuthority("admin")
                .anyRequest().authenticated();

        http.exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler()));

        http.csrf().disable();
    }
}
