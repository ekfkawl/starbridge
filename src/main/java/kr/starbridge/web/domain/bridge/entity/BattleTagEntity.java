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
 * 블랙리스트 (battle tag)
 */
@Entity(name = "tb_blacklist_tag")
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BattleTagEntity implements Serializable {
    /**
     * 아이디, 배틀태그
     */
    @EmbeddedId
    private BattleTagId id;
    /**
     * seq
     */
    private long seq;
    /**
     * 메모
     */
    private String memo;
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
     * 이전 배틀태그
     */
    @Transient
    private String prevTag;
}
