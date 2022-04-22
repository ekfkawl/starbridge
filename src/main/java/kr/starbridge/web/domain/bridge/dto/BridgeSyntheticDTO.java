package kr.starbridge.web.domain.bridge.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class BridgeSyntheticDTO {
    private List<BattleTagDTO> battleTagDTOList;
    private List<IpDTO> ipDTOList;
    private List<RoomFilterDTO> roomFilterDTOList;
    private RoomRoleDTO roomRoleDTO;
    private PlayerDTO playerDTO;
}
