package kr.starbridge.web.domain.bridge.repository;

import kr.starbridge.web.domain.bridge.entity.RoomRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRoleRepository extends JpaRepository<RoomRoleEntity, String> {
}
