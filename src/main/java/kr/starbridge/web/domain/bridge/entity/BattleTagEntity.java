package kr.starbridge.web.domain.bridge.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
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
     * 아이디
     */
    @Id
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
    /**
     * 수정일
     */
    private LocalDateTime modifyDt;
}
