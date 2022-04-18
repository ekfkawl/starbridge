package kr.starbridge.web.global.utils;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.domain.member.entity.MemberEntity;
import kr.starbridge.web.global.common.response.ApiException;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static kr.starbridge.web.global.common.enums.ExceptionEnum.RUNTIME_EXCEPTION;

public class SecurityUtils {

    public static MemberDTO getSelfInfo() {
        MemberEntity memberEntity = (MemberEntity)Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).orElseGet(MemberEntity::new);
        if (StringUtils.isNullOrEmpty(memberEntity.getId())) {
            throw new ApiException(RUNTIME_EXCEPTION);
        }

        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(memberEntity, memberDTO);

        return memberDTO;
    }
}
