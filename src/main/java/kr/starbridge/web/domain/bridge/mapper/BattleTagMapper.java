package kr.starbridge.web.domain.bridge.mapper;

import kr.starbridge.web.domain.bridge.dto.BattleTagDTO;
import kr.starbridge.web.domain.bridge.dto.CommonImportDTO;
import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import kr.starbridge.web.domain.bridge.entity.BattleTagId;

import java.util.ArrayList;
import java.util.List;

public class BattleTagMapper {

    /**
     * BattleTagDTO -> Entity
     *
     * @param battleTagDTO
     * @return
     */
    public static BattleTagEntity toBattleTagEntity(BattleTagDTO battleTagDTO) {
        return BattleTagEntity.builder()
                .id(new BattleTagId(battleTagDTO.getId().getMemberId(), battleTagDTO.getId().getTag()))
                .memo(battleTagDTO.getMemo())
                .isExport(battleTagDTO.isExport())
                .prevTag(battleTagDTO.getPrevTag())
                .build();
    }

    /**
     * BattleTagDTO -> Entity
     * @param battleTagDTOList
     * @return
     */
    public static List<BattleTagEntity> toBattleTagEntity(List<BattleTagDTO> battleTagDTOList) {
        List<BattleTagEntity> res = new ArrayList<>();
        for (BattleTagDTO battleTagDTO : battleTagDTOList) {
            res.add(toBattleTagEntity(battleTagDTO));
        }
        return res;
    }

    /**
     * CommonImportDTO -> Entity
     * @param commonImportDTOList
     * @return
     */
    public static List<BattleTagEntity> toBattleTagEntity(CommonImportDTO commonImportDTOList) {
        List<BattleTagEntity> res = new ArrayList<>();
        List<Long> seq = commonImportDTOList.getSeq();
        for (Long i : seq) {
            res.add(BattleTagEntity.builder()
                    .id(new BattleTagId(commonImportDTOList.getId(), null))
                    .seq(i)
                    .build());
        }
        return res;
    }

    /**
     * Entity -> BattleTagDTO
     *
     * @param battleTagEntity
     * @return
     */
    public static BattleTagDTO toBattleTagDTO(BattleTagEntity battleTagEntity) {
        return BattleTagDTO.builder()
                .id(battleTagEntity.getId())
                .seq(battleTagEntity.getSeq())
                .prevTag(battleTagEntity.getPrevTag())
                .memo(battleTagEntity.getMemo())
                .isExport(battleTagEntity.isExport())
                .build();
    }

    /**
     * Entity -> BattleTagDTO
     *
     * @param battleTagEntities
     * @return
     */
    public static List<BattleTagDTO> toBattleTagDTO(List<BattleTagEntity> battleTagEntities) {
        List<BattleTagDTO> res = new ArrayList<>();
        for (BattleTagEntity battleTagEntity : battleTagEntities) {
            res.add(toBattleTagDTO(battleTagEntity));
        }
        return res;
    }
}