package kr.starbridge.web.domain.bridge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 방 필터
 */
@Entity(name = "tb_room_filter")
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomFilterEntity implements Serializable {
    /**
     * 아이디, 키워드
     */
    @EmbeddedId
    private RoomFilterId id;
    /**
     * seq
     */
    private long seq;
    /**
     * 추출 허용
     */
    private boolean isExport;
    /**
     * 수정일
     */
    @UpdateTimestamp
    private LocalDateTime modifyDt;
    /**
     * 이전 키워드
     */
    @Transient
    private String prevKeyword;
}
