package kr.starbridge.web.domain.member.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "member")
@DynamicInsert
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
