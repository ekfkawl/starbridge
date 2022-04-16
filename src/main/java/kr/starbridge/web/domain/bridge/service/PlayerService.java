package kr.starbridge.web.domain.bridge.service;

import kr.starbridge.web.domain.bridge.entity.PlayerEntity;

import java.util.Optional;

public interface PlayerService {

    /**
     * 플레이어 플래그
     * @param id
     * @return
     */
    Optional<PlayerEntity> getPlayerFlag(String id);

    /**
     * save
     * @param playerEntity
     * @return
     */
    PlayerEntity save(PlayerEntity playerEntity);
}
