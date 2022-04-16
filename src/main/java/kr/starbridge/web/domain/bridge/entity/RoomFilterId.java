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
public class RoomFilterId implements Serializable {

    /**
     * 아이디
     */
    @JsonIgnore
    private String memberId;
    /**
     * 키워드
     */
    private String keyword;
}
