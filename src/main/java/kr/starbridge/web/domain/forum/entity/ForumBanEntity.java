package kr.starbridge.web.domain.forum.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 포럼 아이피 차단
 */
@Entity(name = "tb_forum_ban")
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForumBanEntity {
    /**
     * 아이피
     */
    @Id
    private String ip;
    /**
     * 닉네임
     */
    private String name;
}
