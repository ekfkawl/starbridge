package kr.starbridge.web.domain.bridge.controller;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.bridge.dto.BridgeSyntheticDTO;
import kr.starbridge.web.domain.bridge.entity.*;
import kr.starbridge.web.domain.bridge.mapper.*;
import kr.starbridge.web.domain.bridge.service.*;
import kr.starbridge.web.domain.member.service.MemberService;
import kr.starbridge.web.global.Regex;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.utils.EscapeUtils;
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
    private final MemberService memberService;

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

    /**
     * 클라이언트에서 호출하여 블랙리스트 등록
     * @param id
     * @param pw
     * @param tag
     * @param hash
     * @param memo
     * @return
     */
    @GetMapping("/client/api/ban")
    public ApiResult<Object> clientApiBan(@RequestParam String id, @RequestParam String pw,
                                  @RequestParam(required = false, defaultValue = "") String tag,
                                  @RequestParam(required = false, defaultValue = "") String hash,
                                  @RequestParam(required = false, defaultValue = "") String memo) {

        if (memberService.isMemberForExternal(id, pw)) {
            memo = EscapeUtils.escape(memo);
            /** 배틀태그 upsert */
            if (!StringUtils.isNullOrEmpty(tag) || tag.matches(Regex.BATTLE_TAG)) {
                tag = EscapeUtils.escape(tag);
                BattleTagEntity battleTagEntity = BattleTagEntity.builder()
                        .id(new BattleTagId(id, tag))
                        .prevTag(tag)
                        .memo(memo)
                        .isExport(true)
                        .build();
                if (battleTagService.isExistsTag(id, tag)) {
                    battleTagService.update(battleTagEntity);
                }else {
                    battleTagService.save(battleTagEntity);
                }
            }
            /** 아이피 upsert */
            if (!StringUtils.isNullOrEmpty(hash) || tag.matches(Regex.MD5_HASH)) {
                hash = EscapeUtils.escape(hash);
                IpEntity ipEntity = IpEntity.builder()
                        .id(new IpId(id, hash))
                        .prevHash(hash)
                        .memo(memo)
                        .isExport(true)
                        .build();
                if (ipService.isExistsHash(id, hash)) {
                    ipService.update(ipEntity);
                }else {
                    ipService.save(ipEntity);
                }
            }

            return new ApiResult<>("success");
        }else {
            return new ApiResult<>("false");
        }
    }
}
