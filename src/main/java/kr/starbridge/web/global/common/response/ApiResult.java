package kr.starbridge.web.global.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResult<T> {

    private T data;
    private String viewMsg;
    private boolean success;
}