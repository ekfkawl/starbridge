package kr.starbridge.web.domain.bridge.validator.annotation;

import kr.starbridge.web.domain.bridge.validator.BridgeNotExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 브릿지 관련 중복 체크 어노테이션
 */
@Documented
@Constraint(validatedBy = BridgeNotExistsValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface BridgeNotExists {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> reference();
}
