package kr.starbridge.web.domain.bridge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import kr.starbridge.web.domain.bridge.entity.BattleTagId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
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
}
