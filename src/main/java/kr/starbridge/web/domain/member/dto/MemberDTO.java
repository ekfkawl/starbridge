package kr.starbridge.web.domain.member.dto;

import kr.starbridge.web.domain.member.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
    /**
     * 아이디
     */
    private String id;
    /**
     * 비밀번호
     */
    private String pw;
    /**
     * 이름
     */
    private String name;
    /**
     * 프로필 이미지 주소
     */
    private String img;
    /**
     * 권한
     */
    private RoleEnum auth;
}
