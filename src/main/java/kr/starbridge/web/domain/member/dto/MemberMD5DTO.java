package kr.starbridge.web.domain.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

/**
 * 아이디, 비밀번호 -> MD5 HASH
 */
public class MemberMD5DTO {
    private String md5id;
    private String md5pw;
}
