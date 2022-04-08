package kr.starbridge.web.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor

/**
 * API 응답 상태
 */
public class ApiResult<T> {
    /**
     * JSON으로 출력할 객체
     */
    private T data;
    /**
     * alert 디스플레이 메세지
     */
    private String message;
    /**
     * 리다이렉트 url
     */
    private String href;
    /**
     * 호출 성공 여부
     */
    private boolean success;

    public ApiResult() {
        this.success = true;
    }

    public ApiResult(T data) {
        this.data = data;
        this.success = true;
    }

    public ApiResult(String message) {
        this.message = message;
        this.success = true;
    }

    public ApiResult(T data, String message) {
        this.data = data;
        this.message = message;
        this.success = true;
    }

    public ApiResult(String message, String href) {
        this.message = message;
        this.href = href;
        this.success = true;
    }

    public ApiResult(T data, String message, String href) {
        this.data = data;
        this.message = message;
        this.href = href;
        this.success = true;
    }

    public ApiResult(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }
}