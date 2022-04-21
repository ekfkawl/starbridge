package kr.starbridge.web.domain.forum.repository;

import kr.starbridge.web.domain.forum.entity.ForumCommentEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ForumCommentRepository extends JpaRepository<ForumCommentEntity, Long> {
    List<ForumCommentEntity> findByContentSeq(long contentSeq, Sort sort);

    List<ForumCommentEntity> findByParentComment(long seq);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM tb_forum_comment fc WHERE fc.seq = :seq")
    void deleteBySeq(Long seq);
}
