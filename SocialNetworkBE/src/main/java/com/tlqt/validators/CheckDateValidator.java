/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tlqt.validators;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author Admin
 */
public class CheckDateValidator implements ConstraintValidator<CheckDateFormat, String> {

    private String pattern;

    @Override
    public void initialize(CheckDateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext context) {
        if (object == null) {
            return true;
        }

        DateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        System.out.println(object);
        try {
            dateFormat.parse(object);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
