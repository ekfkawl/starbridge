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
public class RoomRoleDTO {
    /**
     * 아이디
     */
    @JsonIgnore
    private String memberId;
    /**
     * 판수 필터링 적용 여부
     */
    @JsonProperty("enable_history_count")
    private boolean enableHistoryCount;
    /**
     * 판수
     */
    @JsonProperty("history_count")
    private int historyCount;
    /**
     * 승률 필터링 적용 여부
     */
    @JsonProperty("enable_rate")
    private boolean enableRate;
    /**
     * 승률
     */
    @JsonProperty("rate")
    private int rate;
    /**
     * 디스커넥트 필터링 적용 여부
     */
    @JsonProperty("enable_dis")
    private boolean enableDis;
    /**
     * 디스커넥트
     */
    @JsonProperty("dis")
    private int dis;
}
