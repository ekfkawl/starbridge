package kr.starbridge.web.domain.bridge.service;

import kr.starbridge.web.domain.bridge.entity.RoomRoleEntity;

import java.util.Optional;

public interface RoomRoleService {

    /**
     * 방 입장 조건
     * @param id
     * @return
     */
    Optional<RoomRoleEntity> getRole(String id);

    /**
     * save
     * @param roomRoleEntity
     * @return
     */
    RoomRoleEntity save(RoomRoleEntity roomRoleEntity);
}
