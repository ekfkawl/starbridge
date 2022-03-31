package kr.starbridge.web.domain.member.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity(name = "member")
public class MemberEntity {
    /**
     * 아이디
     */
    @Id
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
     * 포럼 관리자 권한
     */
    private Boolean isCm;
    /**
     * 계정 생성일
     */
    private String createDt;

    @Builder
    public MemberEntity(String id, String pw, String name, String img, Boolean isCm, String createDt) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.img = img;
        this.isCm = isCm;
        this.createDt = createDt;
    }
}
