package kr.starbridge.web.global.aop;

import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.domain.member.entity.MemberEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice("kr.starbridge.web.domain")
public class GlobalAdvice {

    /** 로그인 여부를 확인하고 회원 정보를 모델에 추가 */
    @ModelAttribute
    public void addAttrSignIn(@AuthenticationPrincipal MemberEntity principal, Model model) {
        MemberEntity memberEntity = Optional.ofNullable(principal).orElseGet(MemberEntity::new);

        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(memberEntity, memberDTO);

        model.addAttribute("memberDTO", memberDTO);
        model.addAttribute("tick", System.currentTimeMillis());
    }
}
