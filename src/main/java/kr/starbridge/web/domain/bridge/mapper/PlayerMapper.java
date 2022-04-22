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
                .enablePingColor(playerDTO.isEnablePingColor())
                .enableDisProtect(playerDTO.isEnableDisProtect())
                .enableVoting(playerDTO.isEnableVoting())
                .enableTurnColor(playerDTO.isEnableTurnColor())
                .enableOwner(playerDTO.isEnableOwner())
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
                .enablePingColor(playerEntity.isEnablePingColor())
                .enableDisProtect(playerEntity.isEnableDisProtect())
                .enableVoting(playerEntity.isEnableVoting())
                .enableTurnColor(playerEntity.isEnableTurnColor())
                .enableOwner(playerEntity.isEnableOwner())
                .build();
    }
}
