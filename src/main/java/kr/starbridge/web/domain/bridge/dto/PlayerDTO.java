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
    @JsonProperty("ping_color")
    private boolean pingColor;
    /**
     * 무한디스 방지 적용 여부
     */
    @JsonProperty("dis_protect")
    private boolean disProtect;
    /**
     * 추방 투표 커맨드 적용 여부
     */
    @JsonProperty("voting")
    private boolean voting;
    /**
     * 플레이어 색상 적용 여부
     */
    @JsonProperty("turn_color")
    private boolean turnColor;
    /**
     * 소유자 뷰어 적용 여부
     */
    @JsonProperty("owner")
    private boolean owner;
}
