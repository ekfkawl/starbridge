package kr.starbridge.web.global;

import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.domain.member.entity.MemberEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@ControllerAdvice("kr.starbridge.web.domain")
public class GlobalAdvice {

    @Value("${session.user}")
    private String user;

    /**
     * 세션에서 로그인 상태를 가져옴
     * @param session
     * @param model
     */
    @ModelAttribute
    public void addAttrSignin(HttpSession session, Model model) {
        MemberEntity memberEntity = Optional.ofNullable((MemberEntity)session.getAttribute(user)).orElseGet(MemberEntity::new);

        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(memberEntity, memberDTO);
        memberDTO.setId("123");

        model.addAttribute("memberDTO", memberDTO);

        model.addAttribute("tick", System.currentTimeMillis());
    }
}