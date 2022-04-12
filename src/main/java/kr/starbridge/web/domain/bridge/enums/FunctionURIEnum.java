package kr.starbridge.web.domain.bridge.enums;

import lombok.Getter;

@Getter
public enum FunctionURIEnum {
    URI_BLACKLIST_TAG("blacklist-tag"),
    URI_BLACKLIST_TAG_IMPORT("blacklist-tag-import"),
    URI_BLACKLIST_TAG_EXPORT("blacklist-tag-export"),
    URI_BLACKLIST_IP("blacklist-ip"),
    URI_BLACKLIST_IP_IMPORT("blacklist-ip-import"),
    URI_BLACKLIST_IP_EXPORT("blacklist-ip-export"),
    URI_ROOM_FILTER("room-filter"),
    URI_ROOM_FILTER_IMPORT("room-filter-import"),
    URI_ROOM_FILTER_EXPORT("room-filter-export"),
    URI_ROOM_ROLE("room-role"),
    URI_PLAYER("player");

    private String uri;

    FunctionURIEnum(String uri) {
        this.uri = uri;
    }
}
