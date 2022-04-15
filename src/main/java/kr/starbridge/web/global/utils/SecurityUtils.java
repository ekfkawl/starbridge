package kr.starbridge.web.global.utils;

import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.domain.member.entity.MemberEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityUtils {

    public static MemberDTO getSelfInfo() {
        MemberEntity memberEntity = (MemberEntity)Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).orElseGet(MemberEntity::new);

        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(memberEntity, memberDTO);

        return memberDTO;
    }
}
