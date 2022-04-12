package kr.starbridge.web.domain.bridge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattleTagId implements Serializable {

    /**
     * 아이디
     */
    @JsonIgnore
    private String memberId;
    /**
     * 배틀태그
     */
    private String tag;
}
