package kr.starbridge.web.domain.member.service;

import kr.starbridge.web.domain.member.dto.MemberRegisterDTO;
import kr.starbridge.web.domain.member.entity.MemberEntity;
import kr.starbridge.web.global.common.response.ApiResult;

import java.io.IOException;
import java.util.Optional;

public interface MemberService {

    /**
     * 회원 가입
     * @param registerDTO
     * @return
     * @throws IOException
     */
    ApiResult<Object> register(MemberRegisterDTO registerDTO) throws IOException;

    /**
     * 회원 정보
     * @param id
     * @return
     */
    Optional<MemberEntity> getMember(String id);

    /**
     * 전체 회원 수
     * @return
     */
    long getMemberCount();

    /**
     * 아이디 중복 체크
     * @param id
     * @return
     */
    boolean isExistsId(String id);

    /**
     * 닉네임 중복 체크
     * @param name
     * @return
     */
    boolean isExistsName(String name);
}
