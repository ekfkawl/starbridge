package kr.starbridge.web.domain.bridge.repository;

import kr.starbridge.web.domain.bridge.entity.RoomFilterEntity;
import kr.starbridge.web.domain.bridge.entity.RoomFilterId;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface RoomFilterRepository extends JpaRepository<RoomFilterEntity, RoomFilterId> {

    List<RoomFilterEntity> findByIdMemberId(String keyword);

    List<RoomFilterEntity> findByIdMemberId(String keyword, Sort sort);

    RoomFilterEntity findByIdMemberIdAndIdKeyword(String id, String keyword);

    boolean existsByIdMemberIdAndIdKeyword(String id, String keyword);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE tb_room_filter t SET t.id.keyword = :#{#roomFilterEntity.id.keyword}, t.modifyDt = now() WHERE t.id.memberId = :#{#roomFilterEntity.id.memberId} AND t.id.keyword = :#{#roomFilterEntity.prevKeyword}")
    int update(RoomFilterEntity roomFilterEntity);
}
