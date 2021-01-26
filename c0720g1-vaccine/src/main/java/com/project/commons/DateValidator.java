package com.project.commons;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DateValidator implements ConstraintValidator<DateValid, String> {
   private String currentDate;
   private String inputDate;

   public void initialize(DateValid constraint) {
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {

      return false;
   }
}
