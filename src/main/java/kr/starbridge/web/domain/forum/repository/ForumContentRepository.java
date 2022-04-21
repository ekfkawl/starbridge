package kr.starbridge.web.domain.forum.repository;

import kr.starbridge.web.domain.forum.entity.ForumContentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ForumContentRepository extends JpaRepository<ForumContentEntity, Long> {

    List<ForumContentEntity> findAllByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    List<ForumContentEntity> findByIsFixOrderByCreateDtDesc(boolean isFix);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE tb_forum_content fc SET fc.viewCount = fc.viewCount + 1 WHERE fc.seq = :seq")
    int incViewCount(Long seq);
}
