package com.cg.codechallenge.adapter.in.api.trainer.common.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.util.regex.Pattern.CASE_INSENSITIVE;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.regex.Pattern;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import org.springframework.util.StringUtils;

@Constraint(validatedBy = ValidEmail.EmailValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface ValidEmail {

  String message() default
      "email address must follow RFC 5322 standard and have a length not exceeding 320";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final int MAX_EMAIL_LENGTH = 320;

    /** RFC 5322 official standard regex from http://emailregex.com/ */
    private static final Pattern EMAIL_PATTERN =
        Pattern.compile(
            "^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$",
            CASE_INSENSITIVE);

    @Override
    public boolean isValid(
        final String email, final ConstraintValidatorContext constraintValidatorContext) {
      return StringUtils.hasText(email)
          && email.length() < MAX_EMAIL_LENGTH
          && EMAIL_PATTERN.matcher(email).matches();
    }
  }
}
