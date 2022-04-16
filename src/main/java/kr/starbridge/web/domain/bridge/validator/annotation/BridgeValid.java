package kr.starbridge.web.domain.bridge.validator.annotation;

import kr.starbridge.web.domain.bridge.validator.BridgeValidValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 브릿지 관련 유효성 검증 어노테이션
 */
@Documented
@Constraint(validatedBy = BridgeValidValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BridgeValid {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> clazz();
}
