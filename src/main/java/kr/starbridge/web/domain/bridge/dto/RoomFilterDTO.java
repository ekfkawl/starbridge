package kr.starbridge.web.domain.bridge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.starbridge.web.domain.bridge.entity.RoomFilterId;
import kr.starbridge.web.domain.bridge.validator.annotation.BridgeValid;
import kr.starbridge.web.global.Regex;
import lombok.*;

import static kr.starbridge.web.global.utils.EscapeUtils.escape;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@BridgeValid(clazz = RoomFilterDTO.class)
public class RoomFilterDTO {
    /**
     * 아이디, 키워드
     */
    private RoomFilterId id;
    /**
     * seq
     */
    private Long seq;
    /**
     * 이전 배틀태그
     */
    @JsonProperty("prev_keyword")
    private String prevKeyword;
    /**
     * 추출 허용
     */
    @JsonProperty("is_export")
    private boolean isExport;

    public RoomFilterId getId() {
        id.setKeyword(escape(id.getKeyword()));
        return id;
    }
}
