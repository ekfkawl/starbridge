package kr.starbridge.web.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.starbridge.web.domain.member.enums.RoleEnum;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    /**
     * 아이디
     */
    @JsonIgnore
    private String id;
    /**
     * 비밀번호
     */
    @JsonIgnore
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
