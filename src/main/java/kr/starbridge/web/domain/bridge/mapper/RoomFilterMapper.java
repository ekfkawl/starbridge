package kr.starbridge.web.domain.bridge.mapper;

import kr.starbridge.web.domain.bridge.dto.CommonImportDTO;
import kr.starbridge.web.domain.bridge.dto.RoomFilterDTO;
import kr.starbridge.web.domain.bridge.entity.RoomFilterEntity;
import kr.starbridge.web.domain.bridge.entity.RoomFilterId;

import java.util.ArrayList;
import java.util.List;

public class RoomFilterMapper {
    /**
     * roomFilterDTO -> Entity
     *
     * @param roomFilterDTO
     * @return
     */
    public static RoomFilterEntity toRoomFilterEntity(RoomFilterDTO roomFilterDTO) {
        return RoomFilterEntity.builder()
                .id(new RoomFilterId(roomFilterDTO.getId().getMemberId(), roomFilterDTO.getId().getKeyword()))
                .isExport(roomFilterDTO.isExport())
                .prevKeyword(roomFilterDTO.getPrevKeyword())
                .build();
    }

    /**
     * RoomFilterDTO -> Entity
     * @param roomFilterDTOList
     * @return
     */
    public static List<RoomFilterEntity> toRoomFilterEntity(List<RoomFilterDTO> roomFilterDTOList) {
        List<RoomFilterEntity> res = new ArrayList<>();
        for (RoomFilterDTO roomFilterDTO : roomFilterDTOList) {
            res.add(toRoomFilterEntity(roomFilterDTO));
        }
        return res;
    }

    /**
     * CommonImportDTO -> Entity
     * @param commonImportDTOList
     * @return
     */
    public static List<RoomFilterEntity> toRoomFilterEntity(CommonImportDTO commonImportDTOList) {
        List<RoomFilterEntity> res = new ArrayList<>();
        List<Long> seq = commonImportDTOList.getSeq();
        for (Long i : seq) {
            res.add(RoomFilterEntity.builder()
                    .id(new RoomFilterId(commonImportDTOList.getId(), null))
                    .seq(i)
                    .build());
        }
        return res;
    }

    /**
     * Entity -> roomFilterDTO
     *
     * @param roomFilterEntity
     * @return
     */
    public static RoomFilterDTO toRoomFilterDTO(RoomFilterEntity roomFilterEntity) {
        return RoomFilterDTO.builder()
                .id(roomFilterEntity.getId())
                .seq(roomFilterEntity.getSeq())
                .prevKeyword(roomFilterEntity.getPrevKeyword())
                .isExport(roomFilterEntity.isExport())
                .build();
    }

    /**
     * Entity -> RoomFilterDTO
     *
     * @param roomFilterEntities
     * @return
     */
    public static List<RoomFilterDTO> toRoomFilterDTO(List<RoomFilterEntity> roomFilterEntities) {
        List<RoomFilterDTO> res = new ArrayList<>();
        for (RoomFilterEntity roomFilterEntity : roomFilterEntities) {
            res.add(toRoomFilterDTO(roomFilterEntity));
        }
        return res;
    }
}
