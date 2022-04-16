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

/**
 * 플레이어
 */
@Entity(name = "tb_player")
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerEntity implements Serializable {
    /**
     * 아이디
     */
    @Id
    private String memberId;
    /**
     * 미니맵 핑 색상 적용 여부
     */
    private boolean pingColor;
    /**
     * 무한디스 방지 적용 여부
     */
    private boolean disProtect;
    /**
     * 추방 투표 커맨드 적용 여부
     */
    private boolean voting;
    /**
     * 플레이어 색상 적용 여부
     */
    private boolean turnColor;
    /**
     * 소유자 뷰어 적용 여부
     */
    private boolean owner;
}
