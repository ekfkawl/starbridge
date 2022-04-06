package kr.starbridge.web.domain.member.enums;

import lombok.Getter;

/**
 * 유저 권한
 */

@Getter
public enum Role {
    ROLE_ADMIN("admin"), ROLE_USER("user");

    private String description;

    Role(String description) {
        this.description = description;
    }
}
