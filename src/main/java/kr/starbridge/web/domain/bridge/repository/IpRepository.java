package kr.starbridge.web.domain.bridge.repository;

import kr.starbridge.web.domain.bridge.entity.IpEntity;
import kr.starbridge.web.domain.bridge.entity.IpId;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IpRepository extends JpaRepository<IpEntity, IpId> {

    List<IpEntity> findByIdMemberId(String tag);

    List<IpEntity> findByIdMemberId(String tag, Sort sort);

    IpEntity findByIdMemberIdAndIdHash(String id, String hash);

    boolean existsByIdMemberIdAndIdHash(String id, String hash);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE tb_blacklist_ip t SET t.id.hash = :#{#ipEntity.id.hash}, t.memo = :#{#ipEntity.memo}, t.modifyDt = now() WHERE t.id.memberId = :#{#ipEntity.id.memberId} AND t.id.hash = :#{#ipEntity.prevHash}")
    int update(IpEntity ipEntity);
}
