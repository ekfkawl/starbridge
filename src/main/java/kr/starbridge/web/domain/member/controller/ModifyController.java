package kr.starbridge.web.domain.member.controller;

import kr.starbridge.web.domain.member.dto.MemberRegisterDTO;
import kr.starbridge.web.domain.member.service.MemberService;
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
 * 회원 정보수정 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class ModifyController {
    private final MemberService memberService;

    /**
     * 정보수정 뷰로 이동
     * @return
     */
    @GetMapping("/modify")
    public ModelAndView modify() {
        return new ModelAndView("modify");
    }

    /**
     * 정보수정 api
     * @param registerDTO
     * @return
     * @throws IOException
     */
    @PostMapping("/api/modify")
    public ApiResult<Object> apiModify(@Validated(ValidationSequence.class) @RequestBody MemberRegisterDTO registerDTO) throws IOException {

        return memberService.register(registerDTO);
    }

}
