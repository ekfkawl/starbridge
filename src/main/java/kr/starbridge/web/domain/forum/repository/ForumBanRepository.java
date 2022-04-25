package kr.starbridge.web.domain.forum.repository;

import kr.starbridge.web.domain.forum.entity.ForumBanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForumBanRepository extends JpaRepository<ForumBanEntity, String> {
}
