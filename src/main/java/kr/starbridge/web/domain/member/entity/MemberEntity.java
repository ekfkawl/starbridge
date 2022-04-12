package kr.starbridge.web.domain.member.entity;

import kr.starbridge.web.domain.member.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity(name = "tb_member")
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberEntity implements UserDetails, Serializable {
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
     * 권한
     */
    @Enumerated(EnumType.STRING)
    private RoleEnum auth;
    /**
     * 계정 생성일
     */
    private LocalDateTime createDt;
    /**
     * 계정 수정일
     */
    @UpdateTimestamp
    private LocalDateTime modifyDt;
    /**
     * 시큐리티 권한
     */
    @Transient
    private Collection<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.pw;
    }

    @Override
    public String getUsername() {
        return this.id;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
