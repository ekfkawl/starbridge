package kr.starbridge.web.domain.bridge.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDTO {
    /**
     * 아이디
     */
    @JsonIgnore
    private String memberId;
    /**
     * 미니맵 핑 색상 적용 여부
     */
    @JsonProperty("enable_ping_color")
    private boolean enablePingColor;
    /**
     * 무한디스 방지 적용 여부
     */
    @JsonProperty("enable_dis_protect")
    private boolean enableDisProtect;
    /**
     * 추방 투표 커맨드 적용 여부
     */
    @JsonProperty("enable_voting")
    private boolean enableVoting;
    /**
     * 플레이어 색상 적용 여부
     */
    @JsonProperty("enable_turn_color")
    private boolean enableTurnColor;
    /**
     * 소유자 뷰어 적용 여부
     */
    @JsonProperty("enable_owner")
    private boolean enableOwner;
}
