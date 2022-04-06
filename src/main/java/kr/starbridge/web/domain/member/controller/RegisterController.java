package kr.starbridge.web.domain.member.controller;

import kr.starbridge.web.domain.member.dto.MemberRegisterDTO;
import kr.starbridge.web.domain.member.service.impl.MemberServiceImpl;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.common.response.ValidationSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * 회원가입 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final MemberServiceImpl memberService;

    /**
     * 회원가입 뷰로 이동
     * @return
     */
    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }

    /**
     * 회원가입 api
     * @param registerDTO
     * @return
     * @throws IOException
     */
    @PostMapping("/api/register")
    public ApiResult<Object> apiRegister(@Validated(ValidationSequence.class) @RequestBody MemberRegisterDTO registerDTO) throws IOException {

        return memberService.register(registerDTO);
    }

}
