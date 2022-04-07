package kr.starbridge.web.domain.member.controller;

import kr.starbridge.web.global.common.response.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그인 실패 응답 컨트롤러
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public ApiResult<Object> loginFail(@RequestParam(value = "error", required = false) String error,
                                       @RequestParam(value = "exception", required = false) String exception) {

        String message = "true".equals(error) ? exception : null;

        return new ApiResult<>(message, "/");
    }
}
