package kr.starbridge.web.global.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiExceptionEntity {

    private String code;
    private String message;

    @Builder
    public ApiExceptionEntity(String code, String message){
        this.code = code;
        this.message = message;
    }
}