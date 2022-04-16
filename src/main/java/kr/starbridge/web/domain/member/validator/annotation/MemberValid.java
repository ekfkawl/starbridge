package kr.starbridge.web.domain.member.validator.annotation;

import kr.starbridge.web.domain.member.validator.MemberValidValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 회원 유효성 검증 어노테이션
 */
@Documented
@Constraint(validatedBy = MemberValidValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MemberValid {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> clazz();
}
