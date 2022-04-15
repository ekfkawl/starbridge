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
 * 블랙리스트 (ip hash)
 */
@Entity(name = "tb_blacklist_ip")
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IpEntity implements Serializable {
    /**
     * 아이디, 아이피 해시
     */
    @EmbeddedId
    private IpId id;
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
     * 이전 아이피 해시
     */
    @Transient
    private String prevHash;
}
