package kr.starbridge.web.domain.bridge.mapper;

import kr.starbridge.web.domain.bridge.dto.RoomRoleDTO;
import kr.starbridge.web.domain.bridge.entity.RoomRoleEntity;

public class RoomRoleMapper {

    /**
     * roomRoleDTO -> Entity
     * @param roomRoleDTO
     * @return
     */
    public static RoomRoleEntity toRoomRoleEntity(RoomRoleDTO roomRoleDTO) {
        return RoomRoleEntity.builder()
                .memberId(roomRoleDTO.getMemberId())
                .enableHistoryCount(roomRoleDTO.isEnableHistoryCount())
                .historyCount(roomRoleDTO.getHistoryCount())
                .enableRate(roomRoleDTO.isEnableRate())
                .rate(roomRoleDTO.getRate())
                .enableDis(roomRoleDTO.isEnableDis())
                .dis(roomRoleDTO.getDis())
                .build();
    }

    /**
     * Entity -> roomRoleDTO
     * @param roomRoleEntity
     * @return
     */
    public static RoomRoleDTO toRoomRoleDTO(RoomRoleEntity roomRoleEntity) {
        return RoomRoleDTO.builder()
                .memberId(roomRoleEntity.getMemberId())
                .enableHistoryCount(roomRoleEntity.isEnableHistoryCount())
                .historyCount(roomRoleEntity.getHistoryCount())
                .enableRate(roomRoleEntity.isEnableRate())
                .rate(roomRoleEntity.getRate())
                .enableDis(roomRoleEntity.isEnableDis())
                .dis(roomRoleEntity.getDis())
                .build();
    }

}
