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
 * 방 입장 조건
 */
@Entity(name = "tb_room_role")
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRoleEntity implements Serializable {
    /**
     * 아이디
     */
    @Id
    private String memberId;
    /**
     * 판수 필터링 적용 여부
     */
    private boolean enableHistoryCount;
    /**
     * 판수
     */
    private int historyCount;
    /**
     * 승률 필터링 적용 여부
     */
    private boolean enableRate;
    /**
     * 승률
     */
    private int rate;
    /**
     * 디스커넥트 필터링 적용 여부
     */
    private boolean enableDis;
    /**
     * 디스커넥트
     */
    private int dis;
}
