package kr.starbridge.web.domain.bridge.controller;

import kr.starbridge.web.domain.bridge.dto.PlayerDTO;
import kr.starbridge.web.domain.bridge.dto.RoomRoleDTO;
import kr.starbridge.web.domain.bridge.entity.*;
import kr.starbridge.web.domain.bridge.service.*;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

import static kr.starbridge.web.domain.bridge.enums.FunctionURIEnum.*;
import static kr.starbridge.web.domain.bridge.mapper.BattleTagMapper.toBattleTagDTO;
import static kr.starbridge.web.domain.bridge.mapper.IpMapper.toIpDTO;
import static kr.starbridge.web.domain.bridge.mapper.PlayerMapper.toPlayerEntity;
import static kr.starbridge.web.domain.bridge.mapper.RoomFilterMapper.toRoomFilterDTO;
import static kr.starbridge.web.domain.bridge.mapper.RoomRoleMapper.toRoomRoleDTO;

/**
 * bridge 도구 페이지 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class BridgeController extends BridgeBaseController {

    private final BattleTagService battleTagService;
    private final IpService ipService;
    private final RoomFilterService roomFilterService;
    private final RoomRoleService roomRoleService;
    private final PlayerService playerService;

    @GetMapping("/{function}")
    public ModelAndView bridge(@PathVariable(name = "function") String function,
                               @RequestParam(required = false, defaultValue = "") String pull) {

        ModelAndView mv = new ModelAndView("bridge");

        /** 로그인 정보 */
        MemberDTO memberDTO = SecurityUtils.getSelfInfo();

        /** 배틀태그 관련 view */
        if (function.contains(URI_BLACKLIST_TAG.getUri())) {
            List<BattleTagEntity> localBattleTagEntities = battleTagService.getBattleTagsEx(memberDTO.getId());
            mv.addObject("battleTagDTOList", toBattleTagDTO(localBattleTagEntities));

            /** 배틀태그 import view */
            if (URI_BLACKLIST_TAG_IMPORT.getUri().equals(function)) {
                /** import 대상의 추출 허용 배틀태그 리스트 */
                List<BattleTagEntity> importBattleTagEntities = battleTagService.getRemoteExportTags(localBattleTagEntities, pull);
                mv.addObject("importBattleTagDTOList", toBattleTagDTO(importBattleTagEntities));
            }
        }

        /** 아이피 해시 관련 view */
        else if (function.contains(URI_BLACKLIST_IP.getUri())) {
            List<IpEntity> localIpEntities = ipService.getHashesEx(memberDTO.getId());
            mv.addObject("ipDTOList", toIpDTO(localIpEntities));

            /** 아이피 해시 import view */
            if (URI_BLACKLIST_IP_IMPORT.getUri().equals(function)) {
                /** import 대상의 추출 허용 아이피해시 리스트 */
                List<IpEntity> importIpEntities = ipService.getRemoteExportHashes(localIpEntities, pull);
                mv.addObject("importIpDTOList", toIpDTO(importIpEntities));
            }
        }

        /** 방 필터링 관련 view */
        else if (function.contains(URI_ROOM_FILTER.getUri())) {
            List<RoomFilterEntity> localRoomFilterEntities = roomFilterService.getKeywordsEx(memberDTO.getId());
            mv.addObject("roomFilterDTOList", toRoomFilterDTO(localRoomFilterEntities));

            /** 방 필터링 import view */
            if (URI_ROOM_FILTER_IMPORT.getUri().equals(function)) {
                /** import 대상의 추출 허용 키워드 리스트 */
                List<RoomFilterEntity> importRoomFilterEntities = roomFilterService.getRemoteExportKeywords(localRoomFilterEntities, pull);
                mv.addObject("importRoomFilterDTOList", toRoomFilterDTO(importRoomFilterEntities));
            }
        }

        /** 방 입장 조건 관련 view */
        else if (function.contains(URI_ROOM_ROLE.getUri())) {
            Optional<RoomRoleEntity> optionalRoomRoleEntity = roomRoleService.getRole(memberDTO.getId());
            if (optionalRoomRoleEntity.isPresent()) {
                mv.addObject("roomRoleDTO", toRoomRoleDTO(optionalRoomRoleEntity.get()));
            }else {
                mv.addObject("roomRoleDTO", new RoomRoleDTO());
            }
        }

        /** 플레이어 관련 view */
        else if (function.contains(URI_PLAYER.getUri())) {
            Optional<PlayerEntity> optionalPlayerEntity = playerService.getPlayerFlag(memberDTO.getId());
            if (optionalPlayerEntity.isPresent()) {
                mv.addObject("playerDTO", toPlayerEntity(optionalPlayerEntity.get()));
            }else {
                mv.addObject("playerDTO", new PlayerDTO());
            }
        }

        /** js import 경로를 모델에 저장 */
        String uri = "/bridge/" + function;
        mv.addObject("uri", uri);

        /** 추가적으로 서브 js가 있어야하는 페이지 */
        if (function.contains("export") || function.contains("import")) {
            uri =  uri.replace("-export", "");
            uri =  uri.replace("-import", "");
            mv.addObject("subJs", uri);
        }

        return mv;
    }

}
