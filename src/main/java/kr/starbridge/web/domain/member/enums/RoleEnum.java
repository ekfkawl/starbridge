package kr.starbridge.web.domain.member.enums;

import lombok.Getter;

/**
 * 유저 권한
 */

@Getter
public enum RoleEnum {
    ROLE_ADMIN("admin"), ROLE_USER("user");

    private String description;

    RoleEnum(String description) {
        this.description = description;
    }
}
