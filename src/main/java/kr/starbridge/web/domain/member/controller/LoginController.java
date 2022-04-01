package kr.starbridge.web.domain.member.controller;

import kr.starbridge.web.domain.member.dto.MemberMD5DTO;
import kr.starbridge.web.domain.member.entity.MemberEntity;
import kr.starbridge.web.domain.member.service.MemberService;
import kr.starbridge.web.global.common.response.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    @Value("${session.user}")
    private String user;

    /**
     * 로그인 api
     * @param memberMD5DTO
     * @return
     */
    @PostMapping("/api/signin")
    public ApiResult<MemberEntity> apiSiginIn(@RequestBody MemberMD5DTO memberMD5DTO, HttpSession session) {

        ApiResult<MemberEntity> result = memberService.signIn(memberMD5DTO);

        /** 로그인 성공시 세션에 저장 */
        if (result.isSuccess()) {
            session.setAttribute(user, result.getData());
            session.setMaxInactiveInterval(-1);
        }

        return result;
    }

}
