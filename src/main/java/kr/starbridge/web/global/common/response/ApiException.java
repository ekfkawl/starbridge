package kr.starbridge.web.global.common.response;

import kr.starbridge.web.global.common.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
    private ExceptionEnum error;

    public ApiException(ExceptionEnum e) {
        super(e.getMessage());
        this.error = e;
    }
}