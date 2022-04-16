package kr.starbridge.web.domain.bridge.repository;

import kr.starbridge.web.domain.bridge.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, String> {
}
