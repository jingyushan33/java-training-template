package com.lunz.training.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


/**
 * yyyy-MM-dd型日期校验
 * @author puritykid
 * @date 2021-07-20 17:40
 */
public class Date19Valid implements ConstraintValidator<Date19, String> {

   /**
    * yyyy-MM-dd HH:mm:ss 正则
    */

   private static final String REGEX_DATE_PATTERN_19 = "((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s((([0-1][0-9])|(2?[0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))";


   public void initialize(Date14 constraint) {

   }

   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {


      // 为空放行通过
      if (value == null || value.length() == 0) {
         return true;
      }

      return Pattern.matches(REGEX_DATE_PATTERN_19, value);

   }
}
