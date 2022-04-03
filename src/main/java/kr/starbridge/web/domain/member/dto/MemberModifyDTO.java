package kr.starbridge.web.domain.member.dto;

import kr.starbridge.web.global.Regex;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import static kr.starbridge.web.global.common.response.ValidationGroups.NotEmptyGroup;
import static kr.starbridge.web.global.common.response.ValidationGroups.SizekGroup;

@Getter
@Setter
@ToString
public class MemberModifyDTO extends MemberMD5DTO {
    /**
     * 비밀번호
     */
    @NotEmpty(message = "빈 입력란이 존재합니다", groups = NotEmptyGroup.class)
    @Size(min = 5, max = 20, message = "비밀번호는 5~20자여야 합니다", groups = SizekGroup.class)
    private String pw1;
    @NotEmpty(message = "비밀번호를 입력해주세요", groups = NotEmptyGroup.class)
    @Size(min = 5, max = 20, message = "비밀번호는 5~20자여야 합니다", groups = SizekGroup.class)
    private String pw2;
    /**
     * 이름
     */
    @NotEmpty(message = "빈 입력란이 존재합니다", groups = NotEmptyGroup.class)
    @Size(min = 1, max = 20, message = "닉네임은 5~20자여야 합니다", groups = SizekGroup.class)
    private String name;

    public MemberModifyDTO(String pw1, String pw2, String name) {
        /** 공백 제거 */
        pw1 = pw1.replaceAll(Regex.VACUUM, "");
        pw2 = pw2.replaceAll(Regex.VACUUM, "");
        name = name.replaceAll(Regex.VACUUM, "");

        this.pw1 = pw1;
        this.pw2 = pw2;
        this.name = name;

//        super.setMd5id(this.id);
        super.setMd5pw(this.pw1);
    }

}
