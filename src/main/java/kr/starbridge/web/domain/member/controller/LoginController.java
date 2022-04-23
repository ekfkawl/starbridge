package kr.starbridge.web.domain.member.controller;

import kr.starbridge.web.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그인 실패 응답 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;

    @GetMapping("/fail")
    public String loginFail() {
        return String.format("<script>alert('아이디 또는 비밀번호 오류입니다');location.href='/'</script>");
    }

    /**
     * 외부 클라이언트에서 회원 검증 용도
     * @return
     */
    @GetMapping("/member")
    public boolean externalLogin(@RequestParam String id, @RequestParam String pw) {
        return memberService.isMemberForExternal(id, pw) > 0;
    }
}
