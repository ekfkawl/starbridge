package kr.starbridge.web.domain.bridge.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.bridge.entity.BattleTagId;
import kr.starbridge.web.global.Regex;
import lombok.*;

import static kr.starbridge.web.global.utils.EscapeUtils.*;

@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
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

    /**
     * 배틀태그 유효성 체크
     * @return
     */
    public boolean isNotBattleTag() {
        String tag = unescape(this.id.getTag());
        return StringUtils.isNullOrEmpty(tag) || !tag.matches(Regex.BATTLE_TAG);
    }
}
