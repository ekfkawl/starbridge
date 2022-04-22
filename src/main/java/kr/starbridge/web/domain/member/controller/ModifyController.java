package kr.starbridge.web.domain.member.controller;

import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.domain.member.dto.MemberModifyDTO;
import kr.starbridge.web.domain.member.mapper.MemberMapper;
import kr.starbridge.web.domain.member.service.MemberService;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.common.response.ValidationSequence;
import kr.starbridge.web.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
     * @param modifyDTO
     * @return
     */
    @PutMapping("/api/modify")
    public ApiResult<Object> apiModify(@Validated(ValidationSequence.class) @RequestBody MemberModifyDTO modifyDTO) {

        /** 로그인 정보 */
        MemberDTO oldMemberDTO = SecurityUtils.getSelfInfo();

        modifyDTO.setId(oldMemberDTO.getId());
        modifyDTO.setAuth(oldMemberDTO.getAuth());
        return memberService.modify(MemberMapper.toMemberEntity(modifyDTO));
    }

}
