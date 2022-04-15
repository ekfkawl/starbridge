package kr.starbridge.web.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.starbridge.web.domain.member.validator.annotation.MemberNotExists;
import kr.starbridge.web.global.Regex;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static kr.starbridge.web.global.common.response.ValidationGroups.NotEmptyGroup;
import static kr.starbridge.web.global.common.response.ValidationGroups.SizeGroup;

@Getter
@Setter
@ToString
@MemberNotExists(reference = MemberRegisterDTO.class)
public class MemberRegisterDTO extends MemberMD5DTO {
    /**
     * 아이디
     */
    @NotEmpty(message = "빈 입력란이 존재합니다", groups = NotEmptyGroup.class)
    @Size(min = 5, max = 20, message = "아이디는 5~20자여야 합니다", groups = SizeGroup.class)
    private String id;
    /**
     * 비밀번호
     */
    @NotEmpty(message = "빈 입력란이 존재합니다", groups = NotEmptyGroup.class)
    @Size(min = 5, max = 20, message = "비밀번호는 5~20자여야 합니다", groups = SizeGroup.class)
    private String pw1;
    @NotEmpty(message = "비밀번호를 입력해주세요", groups = NotEmptyGroup.class)
    @Size(min = 5, max = 20, message = "비밀번호는 5~20자여야 합니다", groups = SizeGroup.class)
    private String pw2;
    /**
     * 이름
     */
    @NotEmpty(message = "빈 입력란이 존재합니다", groups = NotEmptyGroup.class)
    @Size(min = 1, max = 20, message = "닉네임은 5~20자여야 합니다", groups = SizeGroup.class)
    private String name;
    /**
     * Recaptcha 검증
     */
    @NotEmpty(message = "reCAPTCHA 스팸방지 기능을 체크해 주십시오", groups = NotEmptyGroup.class)
    @JsonProperty("recaptcha")
    private String gRecaptchaResponse;

    public MemberRegisterDTO(String id, String pw1, String pw2, String name, String gRecaptchaResponse) {
        /** 공백 제거 */
        id = id.replaceAll(Regex.VACUUM, "");
        pw1 = pw1.replaceAll(Regex.VACUUM, "");
        pw2 = pw2.replaceAll(Regex.VACUUM, "");
        name = name.replaceAll(Regex.VACUUM, "");

        this.id = id;
        this.pw1 = pw1;
        this.pw2 = pw2;
        this.name = name;
        this.gRecaptchaResponse = gRecaptchaResponse;

        super.setMd5id(this.id);
        super.setMd5pw(this.pw1);
    }

}
