/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
public class NotNullValidator implements ConstraintValidator<NotNull, Object> {

    private String message;
    
    @Override
    public void initialize(NotNull constraintAnnotation) {
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value.equals("")) {
            return false;
        }
        
        return true;
    }
    
}
