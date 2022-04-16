package kr.starbridge.web.domain.bridge.controller;

import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import kr.starbridge.web.domain.bridge.entity.IpEntity;
import kr.starbridge.web.domain.bridge.entity.RoomFilterEntity;
import kr.starbridge.web.domain.bridge.service.BattleTagService;
import kr.starbridge.web.domain.bridge.service.IpService;
import kr.starbridge.web.domain.bridge.service.RoomFilterService;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static kr.starbridge.web.domain.bridge.enums.FunctionURIEnum.*;
import static kr.starbridge.web.domain.bridge.mapper.BattleTagMapper.toBattleTagDTO;
import static kr.starbridge.web.domain.bridge.mapper.IpMapper.toIpDTO;
import static kr.starbridge.web.domain.bridge.mapper.RoomFilterMapper.toRoomFilterDTO;

/**
 * bridge 도구 페이지 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class BridgeController extends BridgeBaseController {

    private final BattleTagService battleTagService;
    private final IpService ipService;
    private final RoomFilterService roomFilterService;

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
        if (function.contains(URI_BLACKLIST_IP.getUri())) {
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
        if (function.contains(URI_ROOM_FILTER.getUri())) {
            List<RoomFilterEntity> localRoomFilterEntities = roomFilterService.getKeywordsEx(memberDTO.getId());
            mv.addObject("roomFilterDTOList", toRoomFilterDTO(localRoomFilterEntities));

            /** 방 필터링 import view */
            if (URI_BLACKLIST_IP_IMPORT.getUri().equals(function)) {
                /** import 대상의 추출 허용 키워드 리스트 */
                List<RoomFilterEntity> importRoomFilterEntities = roomFilterService.getRemoteExportKeywords(localRoomFilterEntities, pull);
                mv.addObject("importRoomFilterDTOList", toRoomFilterDTO(importRoomFilterEntities));
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
