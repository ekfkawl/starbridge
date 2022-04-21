package kr.starbridge.web.domain.forum.service;

import kr.starbridge.web.domain.forum.entity.ForumCommentEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ForumCommentService {

    /**
     * 게시물의 전체 댓글
     * @param contentSeq
     * @return
     */
    List<ForumCommentEntity> getComments(long contentSeq);

    /**
     * 게시물의 특정 댓글
     * @param seq
     * @return
     */
    Optional<ForumCommentEntity> getComment(long seq);

    /**
     * 댓글 작성자 정보
     * @param seq
     * @return
     */
    HashMap<String, Object> getCommentWriter(long seq);

    /**
     * save
     * @return
     */
    ForumCommentEntity save(ForumCommentEntity forumCommentEntity);

    /**
     * delete
     * @param forumCommentEntity
     */
    void delete(ForumCommentEntity forumCommentEntity);
}
