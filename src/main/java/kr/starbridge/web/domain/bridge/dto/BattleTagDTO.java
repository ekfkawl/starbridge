package kr.starbridge.web.domain.bridge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.starbridge.web.domain.bridge.entity.BattleTagId;
import kr.starbridge.web.domain.bridge.validator.annotation.BridgeValid;
import lombok.*;

import static kr.starbridge.web.global.utils.EscapeUtils.escape;

@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@BridgeValid(clazz = BattleTagDTO.class)
public class BattleTagDTO {
    /**
     * 아이디/배틀태그
     */
    private BattleTagId id;
    /**
     * seq
     */
    private long seq;
    /**
     * 이전 배틀태그
     */
    @JsonProperty("prev_tag")
    private String prevTag;
    /**
     * 메모
     */
    private String memo;
    /**
     * 추출 허용
     */
    @JsonProperty("is_export")
    private boolean isExport;

    public BattleTagId getId() {
        id.setTag(escape(id.getTag()));
        return id;
    }

    public String getPrevTag() {
        return escape(prevTag);
    }

    public String getMemo() {
        return escape(memo);
    }
}
