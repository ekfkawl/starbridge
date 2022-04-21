package kr.starbridge.web.domain.forum.validator.annotation;

import kr.starbridge.web.domain.forum.validator.ForumRequestValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 포럼 request 검증 어노테이션
 */
@Documented
@Constraint(validatedBy = ForumRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ForumRequest {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> clazz();
}
