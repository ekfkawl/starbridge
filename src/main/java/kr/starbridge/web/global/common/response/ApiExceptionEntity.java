package kr.starbridge.web.global.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiExceptionEntity {
    /**
     * 예외 코드
     */
    private String code;
    /**
     * 예외 메세지
     */
    private String message;

    @Builder
    public ApiExceptionEntity(String code, String message){
        this.code = code;
        this.message = message;
    }
}