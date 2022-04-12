package kr.starbridge.web.domain.bridge.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BattleTagImportDTO {
    /**
     * 아이디
     */
    private String id;
    /**
     * seq
     */
    private List<Long> seq;
}

