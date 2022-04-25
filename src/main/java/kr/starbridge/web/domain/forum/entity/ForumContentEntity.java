package kr.starbridge.web.domain.forum.entity;

import kr.starbridge.web.domain.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 포럼 게시물
 */
@Entity(name = "tb_forum_content")
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForumContentEntity implements Serializable {
    /**
     * seq
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    /**
     * Member
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private MemberEntity member;
    /**
     * 카테고리
     */
    private int category;
    /**
     * 제목
     */
    private String title;
    /**
     * 내용
     */
    @Lob
    private String content;
    /**
     * 조회수
     */
    private int viewCount;
    /**
     * 상단 고정
     */
    private boolean isFix;
    /**
     * ip
     */
    private String ip;
    /**
     * 생성일
     */
    @CreationTimestamp
    private LocalDateTime createDt;
    /**
     * 수정일
     */
    @UpdateTimestamp
    private LocalDateTime modifyDt;
    /**
     * Comment
     */
    @OneToMany(mappedBy = "content", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<ForumCommentEntity> comment;
}
