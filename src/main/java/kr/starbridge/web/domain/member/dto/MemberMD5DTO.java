package kr.starbridge.web.domain.member.dto;

import kr.starbridge.web.global.utils.GenerateUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
/**
 * 아이디, 비밀번호 -> MD5 HASH
 */
public class MemberMD5DTO {

    private String md5id;
    private String md5pw;

    public String getMd5id() {
        return GenerateUtils.StrToMD5(md5id);
    }

    public String getMd5pw() {
        return GenerateUtils.StrToMD5(md5pw);
    }
}
