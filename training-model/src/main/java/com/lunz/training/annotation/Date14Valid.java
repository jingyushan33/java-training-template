package com.lunz.training.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;


/**
 * yyyy-MM-dd型日期校验
 * @author puritykid
 * @date 2021-07-20 17:40
 */
public class Date14Valid implements ConstraintValidator<Date14, String> {

   /**
    * yyyy-MM-dd 正则
    */

   private static final String REGEX_DATE_PATTERN_14 = "((\\d{2}(([02468][048])|([13579][26]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-]((((0?[13578])|(1[02]))[\\-]((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-]((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-]((0?[1-9])|(1[0-9])|(2[0-8]))))))";


   @Override
   public void initialize(Date14 constraint) {

   }

   @Override
   public boolean isValid(String value, ConstraintValidatorContext context) {


      // 为空放行通过
      if (value == null || value.length() == 0) {
         return true;
      }

      return Pattern.matches(REGEX_DATE_PATTERN_14, value);

   }
}
