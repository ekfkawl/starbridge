package kr.starbridge.web.domain.bridge.mapper;

import kr.starbridge.web.domain.bridge.dto.BattleTagDTO;
import kr.starbridge.web.domain.bridge.dto.CommonImportDTO;
import kr.starbridge.web.domain.bridge.dto.IpDTO;
import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import kr.starbridge.web.domain.bridge.entity.BattleTagId;
import kr.starbridge.web.domain.bridge.entity.IpEntity;
import kr.starbridge.web.domain.bridge.entity.IpId;

import java.util.ArrayList;
import java.util.List;

public class IpMapper {
    /**
     * ipDTO -> Entity
     *
     * @param ipDTO
     * @return
     */
    public static IpEntity toIpEntity(IpDTO ipDTO) {
        return IpEntity.builder()
                .id(new IpId(ipDTO.getId().getMemberId(), ipDTO.getId().getHash()))
                .memo(ipDTO.getMemo())
                .isExport(ipDTO.isExport())
                .prevHash(ipDTO.getPrevHash())
                .build();
    }

    /**
     * IpDTO -> Entity
     * @param ipDTOList
     * @return
     */
    public static List<IpEntity> toIpEntity(List<IpDTO> ipDTOList) {
        List<IpEntity> res = new ArrayList<>();
        for (IpDTO ipDTO : ipDTOList) {
            res.add(toIpEntity(ipDTO));
        }
        return res;
    }

    /**
     * CommonImportDTO -> Entity
     * @param commonImportDTOList
     * @return
     */
    public static List<IpEntity> toIpEntity(CommonImportDTO commonImportDTOList) {
        List<IpEntity> res = new ArrayList<>();
        List<Long> seq = commonImportDTOList.getSeq();
        for (Long i : seq) {
            res.add(IpEntity.builder()
                    .id(new IpId(commonImportDTOList.getId(), null))
                    .seq(i)
                    .build());
        }
        return res;
    }

    /**
     * Entity -> ipDTO
     *
     * @param ipEntity
     * @return
     */
    public static IpDTO toIpDTO(IpEntity ipEntity) {
        return IpDTO.builder()
                .id(ipEntity.getId())
                .seq(ipEntity.getSeq())
                .prevHash(ipEntity.getPrevHash())
                .memo(ipEntity.getMemo())
                .isExport(ipEntity.isExport())
                .build();
    }

    /**
     * Entity -> IpDTO
     *
     * @param ipEntities
     * @return
     */
    public static List<IpDTO> toIpDTO(List<IpEntity> ipEntities) {
        List<IpDTO> res = new ArrayList<>();
        for (IpEntity ipEntity : ipEntities) {
            res.add(toIpDTO(ipEntity));
        }
        return res;
    }
}
