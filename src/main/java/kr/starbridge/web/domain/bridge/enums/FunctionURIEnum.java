package kr.starbridge.web.domain.bridge.enums;

import lombok.Getter;

@Getter
public enum FunctionURIEnum {
    URI_BLACKLIST_TAG("blacklist-tag"),
    URI_BLACKLIST_IP("blacklist-ip"),
    URI_ROOM_FILTER("room-filter"),
    URI_ROOM_ROLE("room-role"),
    URI_PLAYER("player");

    private String uri;

    FunctionURIEnum(String uri) {
        this.uri = uri;
    }
}
