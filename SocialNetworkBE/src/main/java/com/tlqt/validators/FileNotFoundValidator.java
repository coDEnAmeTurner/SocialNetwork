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
public class FileNotFoundValidator implements ConstraintValidator<FileNotFound, MultipartFile> {

    private String message;
    
    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value.getOriginalFilename().isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(FileNotFound constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

}
