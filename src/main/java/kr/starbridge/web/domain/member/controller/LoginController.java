package kr.starbridge.web.domain.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그인 실패 응답 컨트롤러
 */
@RestController
public class LoginController {

    @GetMapping("/fail")
    public String loginFail() {
        return String.format("<script>alert('아이디 또는 비밀번호 오류입니다');location.href='/'</script>");
    }
}
