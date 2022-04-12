package kr.starbridge.web.domain.bridge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
     * 이전 배틀태그
     */
    private String prevTag;
    /**
     * 메모
     */
    private String memo;
    /**
     * 추출 허용
     */
    private boolean isExport;
}
