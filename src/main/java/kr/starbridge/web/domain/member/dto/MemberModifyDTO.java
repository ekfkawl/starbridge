package kr.starbridge.web.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.member.enums.RoleEnum;
import kr.starbridge.web.domain.member.validator.annotation.MemberValid;
import kr.starbridge.web.global.Regex;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.validator.routines.UrlValidator;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.regex.Pattern;

import static kr.starbridge.web.global.common.response.ValidationGroups.NotEmptyGroup;
import static kr.starbridge.web.global.common.response.ValidationGroups.SizeGroup;

@Getter
@Setter
@ToString
@MemberValid(clazz = MemberModifyDTO.class)
public class MemberModifyDTO extends MemberMD5DTO {
    /**
     * 아이디
     */
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
     * 변경 전 이름
     */
    @JsonProperty("prev_name")
    private String prevName;
    /**
     * 프로필 이미지 주소
     */
    private String img;
    /**
     * 권한
     */
    @JsonIgnore
    private RoleEnum auth;

    public MemberModifyDTO(String pw1, String pw2, String name, String img) {
        /** 공백 제거 */
        pw1 = pw1.replaceAll(Regex.VACUUM, "");
        pw2 = pw2.replaceAll(Regex.VACUUM, "");
        name = name.replaceAll(Regex.VACUUM, "");

        /** 프로필 이미지 확장자 검증 */
        if (!StringUtils.isNullOrEmpty(img)) {
            String tmp = img.replaceAll(Regex.VACUUM, "");
            UrlValidator urlValidator = new UrlValidator();
            if (urlValidator.isValid(tmp)) {
                img = Pattern.matches(Regex.IS_IMAGE, tmp) ? tmp : null;
            }else {
                img = null;
            }
        }else {
            img = null;
        }

        this.pw1 = pw1;
        this.pw2 = pw2;
        this.name = name;
        this.img = img;

        super.setMd5pw(this.pw1);
    }

}
