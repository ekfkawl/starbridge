package kr.starbridge.web.domain.member.controller;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.domain.member.dto.MemberModifyDTO;
import kr.starbridge.web.domain.member.service.MemberService;
import kr.starbridge.web.global.common.response.ApiException;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.common.response.ValidationSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import static kr.starbridge.web.global.common.enums.ExceptionEnum.RUNTIME_EXCEPTION;

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
    public ApiResult<Object> apiModify(@Validated(ValidationSequence.class) @RequestBody MemberModifyDTO modifyDTO, Model model) {

        MemberDTO oldMemberDTO = (MemberDTO)model.getAttribute("memberDTO");
        if (StringUtils.isNullOrEmpty(oldMemberDTO.getId())) {
            throw new ApiException(RUNTIME_EXCEPTION);
        }

        return memberService.modify(modifyDTO, oldMemberDTO);
    }

}
