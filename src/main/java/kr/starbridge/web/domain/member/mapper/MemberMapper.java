package kr.starbridge.web.domain.member.mapper;

import kr.starbridge.web.domain.member.dto.MemberDTO;
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

    /**
     * MemberDTO -> Entity
     * @param memberDTO
     * @return
     */
    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        return MemberEntity.builder()
                .id(memberDTO.getId())
                .pw(memberDTO.getPw())
                .name(memberDTO.getName())
                .img(memberDTO.getImg())
                .auth(memberDTO.getAuth())
                .build();
    }

    /**
     * Entity -> MemberDTO
     * @param memberEntity
     * @return
     */
    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        return MemberDTO.builder()
                .id(memberEntity.getId())
                .pw(memberEntity.getPw())
                .name(memberEntity.getName())
                .img(memberEntity.getImg())
                .auth(memberEntity.getAuth())
                .build();
    }
}
