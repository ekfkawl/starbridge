package kr.starbridge.web.domain.forum.entity;

import kr.starbridge.web.domain.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 포럼 댓글
 */
@Entity(name = "tb_forum_comment")
@DynamicInsert
@DynamicUpdate
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForumCommentEntity implements Serializable {
    /**
     * seq
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    /**
     * content_seq
     */
    @Column(name = "content_seq")
    private Long contentSeq;
    /**
     * Member
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private MemberEntity member;
    /**
     * 내용
     */
    @Lob
    private String comment;
    /**
     * 상위 댓글 seq
     */
    private long parentComment;
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
     * Content
     */
    @ManyToOne
    @JoinColumn(name = "content_seq", insertable = false, updatable = false)
    private ForumContentEntity content;
}
