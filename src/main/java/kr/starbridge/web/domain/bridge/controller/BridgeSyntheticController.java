package kr.starbridge.web.domain.bridge.controller;

import kr.starbridge.web.domain.bridge.dto.BridgeSyntheticDTO;
import kr.starbridge.web.domain.bridge.entity.PlayerEntity;
import kr.starbridge.web.domain.bridge.entity.RoomRoleEntity;
import kr.starbridge.web.domain.bridge.mapper.*;
import kr.starbridge.web.domain.bridge.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BridgeSyntheticController extends BridgeBaseController {

    private final BattleTagService battleTagService;
    private final IpService ipService;
    private final RoomFilterService roomFilterService;
    private final RoomRoleService roomRoleService;
    private final PlayerService playerService;

    /**
     * 클라이언트에서 파싱할 종합 데이터
     * @param id
     * @return
     */
    @GetMapping("/client/api/bridge")
    public BridgeSyntheticDTO bridge(@RequestParam String id) {

        BridgeSyntheticDTO bridgeSyntheticDTO = new BridgeSyntheticDTO();

        bridgeSyntheticDTO.setBattleTagDTOList(BattleTagMapper.toBattleTagDTO(battleTagService.getBattleTags(id)));
        bridgeSyntheticDTO.setIpDTOList(IpMapper.toIpDTO(ipService.getHashes(id)));
        bridgeSyntheticDTO.setRoomFilterDTOList(RoomFilterMapper.toRoomFilterDTO(roomFilterService.getKeywords(id)));

        Optional<RoomRoleEntity> optionalRoomRoleEntity = roomRoleService.getRole(id);
        if (optionalRoomRoleEntity.isPresent()) {
            bridgeSyntheticDTO.setRoomRoleDTO(RoomRoleMapper.toRoomRoleDTO(optionalRoomRoleEntity.get()));
        }

        Optional<PlayerEntity> optionalPlayerEntity = playerService.getPlayerFlag(id);
        if (optionalPlayerEntity.isPresent()) {
            bridgeSyntheticDTO.setPlayerDTO(PlayerMapper.toPlayerEntity(optionalPlayerEntity.get()));
        }

        return bridgeSyntheticDTO;
    }

}
