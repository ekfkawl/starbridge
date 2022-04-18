package kr.starbridge.web.domain.forum.repository;

import kr.starbridge.web.domain.forum.entity.ForumContentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ForumContentRepository extends JpaRepository<ForumContentEntity, Long> {

    List<ForumContentEntity> findAllByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
