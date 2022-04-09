package kr.starbridge.web.domain.bridge.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BattleTagDTO {
    /**
     * 아이디
     */
    private String id;
    /**
     * 아이피 (md5)
     */
    private String hash;
    /**
     * 메모
     */
    private String memo;
    /**
     * 추출 허용
     */
    private boolean isExport;
}
