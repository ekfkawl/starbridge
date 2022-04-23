package kr.starbridge.web.domain.bridge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import kr.starbridge.web.domain.bridge.entity.IpId;
import kr.starbridge.web.domain.bridge.validator.annotation.BridgeValid;
import lombok.*;

import static kr.starbridge.web.global.utils.EscapeUtils.escape;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@BridgeValid(clazz = IpDTO.class)
public class IpDTO {
    /**
     * 아이디/아이피해시
     */
    private IpId id;
    /**
     * seq
     */
    private Long seq;
    /**
     * 이전 배틀태그
     */
    @JsonProperty("prev_hash")
    private String prevHash;
    /**
     * 메모
     */
    private String memo;
    /**
     * 추출 허용
     */
    @JsonProperty("is_export")
    private boolean isExport;

    public String getMemo() {
        return escape(memo);
    }
}
