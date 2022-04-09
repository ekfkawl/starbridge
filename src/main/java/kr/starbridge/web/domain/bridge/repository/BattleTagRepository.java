package kr.starbridge.web.domain.bridge.repository;

import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattleTagRepository extends JpaRepository<BattleTagEntity, String> {
}
