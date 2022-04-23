package kr.starbridge.web.domain.member.dto;

import kr.starbridge.web.global.utils.GenerateUtils;
import lombok.*;

/**
 * 아이디 -> MD5 HASH
 * 비밀번호 -> MD5 HASH
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberMD5DTO {

    private String md5id;
    private String md5pw;

    public String getMd5id() {
        return GenerateUtils.StrToMD5(md5id);
    }

    public String getMd5pw() {
        return GenerateUtils.StrToMD5(md5pw);
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        return passwordEncoder.encode(GenerateUtils.StrToMD5(md5pw));
    }
}
