package kr.starbridge.web.domain.bridge.mapper;

import kr.starbridge.web.domain.bridge.dto.PlayerDTO;
import kr.starbridge.web.domain.bridge.entity.PlayerEntity;

public class PlayerMapper {
    /**
     * playerDTO -> Entity
     * @param playerDTO
     * @return
     */
    public static PlayerEntity toPlayerEntity(PlayerDTO playerDTO) {
        return PlayerEntity.builder()
                .memberId(playerDTO.getMemberId())
                .pingColor(playerDTO.isPingColor())
                .disProtect(playerDTO.isDisProtect())
                .voting(playerDTO.isVoting())
                .turnColor(playerDTO.isTurnColor())
                .owner(playerDTO.isOwner())
                .build();
    }

    /**
     * Entity -> PlayerDTO
     * @param playerEntity
     * @return
     */
    public static PlayerDTO toPlayerEntity(PlayerEntity playerEntity) {
        return PlayerDTO.builder()
                .memberId(playerEntity.getMemberId())
                .pingColor(playerEntity.isPingColor())
                .disProtect(playerEntity.isDisProtect())
                .voting(playerEntity.isVoting())
                .turnColor(playerEntity.isTurnColor())
                .owner(playerEntity.isOwner())
                .build();
    }
}
