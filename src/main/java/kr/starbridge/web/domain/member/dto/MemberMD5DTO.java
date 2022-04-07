package kr.starbridge.web.domain.member.dto;

import kr.starbridge.web.global.utils.GenerateUtils;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 아이디, 비밀번호 -> MD5 HASH
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
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(GenerateUtils.StrToMD5(md5pw));
    }
}
