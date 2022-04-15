package kr.starbridge.web.domain.member.validator.annotation;

import kr.starbridge.web.domain.member.validator.MemberNotExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 회원 중복 체크 어노테이션
 */
@Documented
@Constraint(validatedBy = MemberNotExistsValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberNotExists {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> reference();
}