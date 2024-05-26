/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.configs;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 *
 * @author Admin
 */
@Configuration //danh dau day la ro dau
@EnableWebMvc //bat kha nang xai nhung gi co san
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.tlqt.controllers",
    "com.tlqt.repositories",
    "com.tlqt.services",
}) //chi dinh vi tri xai annotation lien quan ro dau nay
public class WebAppContextConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    } //bat kha nang doc hieu endpoint "/"

    //pt tao dau InternalResourceViewResolver, dung de chi cach tim file jsp
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver r = new InternalResourceViewResolver();
        r.setViewClass(JstlView.class); //config engine tim file
        r.setPrefix("/WEB-INF/pages/");//config vi tri thu muc
        r.setSuffix(".jsp"); //config hau to cua file

        return r;
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver
                = new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }
    
    
}
