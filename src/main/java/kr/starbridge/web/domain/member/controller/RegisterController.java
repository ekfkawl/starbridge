package kr.starbridge.web.domain.member.controller;

import kr.starbridge.web.domain.member.dto.MemberRegisterDTO;
import kr.starbridge.web.domain.member.mapper.MemberMapper;
import kr.starbridge.web.domain.member.service.MemberService;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.common.response.ValidationSequence;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 회원가입 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final MemberService memberService;

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
     * @throws ParseException
     */
    @PostMapping("/api/register")
    public ApiResult<Object> apiRegister(@Validated(ValidationSequence.class) @RequestBody MemberRegisterDTO registerDTO) throws ParseException {
        return memberService.register(MemberMapper.toMemberEntity(registerDTO), registerDTO.getGRecaptchaResponse());
    }

}
