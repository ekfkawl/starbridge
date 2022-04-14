package kr.starbridge.web.domain.member.mapper;

import kr.starbridge.web.domain.member.dto.MemberModifyDTO;
import kr.starbridge.web.domain.member.dto.MemberRegisterDTO;
import kr.starbridge.web.domain.member.entity.MemberEntity;

public class MemberMapper {

    /**
     * MemberRegisterDTO -> Entity
     * @param registerDTO
     * @return
     */
    public static MemberEntity toMemberEntity(MemberRegisterDTO registerDTO) {
        return MemberEntity.builder()
                .id(registerDTO.getMd5id())
                .pw(registerDTO.getMd5pw())
                .name(registerDTO.getName())
                .build();
    }

    /**
     * MemberModifyDTO -> Entity
     * @param modifyDTO
     * @return
     */
    public static MemberEntity toMemberEntity(MemberModifyDTO modifyDTO) {
        return MemberEntity.builder()
                .id(modifyDTO.getId())
                .pw(modifyDTO.getMd5pw())
                .name(modifyDTO.getName())
                .auth(modifyDTO.getAuth())
                .build();
    }
}
