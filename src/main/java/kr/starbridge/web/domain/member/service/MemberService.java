package kr.starbridge.web.domain.member.service;

import kr.starbridge.web.domain.member.entity.MemberEntity;
import kr.starbridge.web.global.common.response.ApiResult;
import org.json.simple.parser.ParseException;

import java.util.Optional;

public interface MemberService {

    /**
     * 회원 가입
     * @param memberEntity
     * @param recaptcha
     * @return
     * @throws ParseException
     */
    ApiResult<Object> register(MemberEntity memberEntity, String recaptcha) throws ParseException;

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

    /**
     * 회원 정보 수정
     * @param memberEntity
     * @return
     */
    ApiResult<Object> modify(MemberEntity memberEntity);

    /**
     * 외부에서 로그인 체크
     * @param id
     * @param pw
     * @return
     */
    boolean isMemberForExternal(String id, String pw);

    /**
     * save
     * @param memberEntity
     * @return
     */
    MemberEntity save(MemberEntity memberEntity);

}
