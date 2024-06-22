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
public class InvalidImageValidator implements ConstraintValidator<InvalidImage, MultipartFile> {

    private String[] types;

    @Override
    public void initialize(InvalidImage constraintAnnotation) {
        this.types = constraintAnnotation.type();
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        Boolean bl = false;

        if (value.getOriginalFilename().isEmpty()) {
            bl = true;
        }
        
        for (String type : types) {
            if (type.equals(value.getContentType())) {
                return true;
            }
        }

        return bl;
    }

}
