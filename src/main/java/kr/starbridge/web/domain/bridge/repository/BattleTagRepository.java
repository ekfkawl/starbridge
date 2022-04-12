package kr.starbridge.web.domain.bridge.repository;

import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import kr.starbridge.web.domain.bridge.entity.BattleTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BattleTagRepository extends JpaRepository<BattleTagEntity, BattleTagId> {

    List<BattleTagEntity> findByIdMemberId(String tag);

    BattleTagEntity findByIdMemberIdAndIdTag(String id, String tag);

    boolean existsByIdMemberIdAndIdTag(String id, String tag);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE tb_blacklist_tag t SET t.id.tag = :#{#battleTagEntity.id.tag}, t.memo = :#{#battleTagEntity.memo}, t.modifyDt = now() WHERE t.id.memberId = :#{#battleTagEntity.id.memberId} AND t.id.tag = :#{#battleTagEntity.prevTag}")
    int update(BattleTagEntity battleTagEntity);
}
