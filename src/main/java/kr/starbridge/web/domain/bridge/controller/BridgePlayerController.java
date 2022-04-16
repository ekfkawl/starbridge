package kr.starbridge.web.domain.bridge.controller;

import kr.starbridge.web.domain.bridge.dto.PlayerDTO;
import kr.starbridge.web.domain.bridge.service.PlayerService;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static kr.starbridge.web.domain.bridge.mapper.PlayerMapper.toPlayerEntity;

/**
 * 플레이어 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class BridgePlayerController extends BridgeBaseController {

    private final PlayerService playerService;

    @PostMapping("/api/player")
    public ApiResult<PlayerDTO> apiUpsertPlayerFlag(@RequestBody PlayerDTO playerDTO) {

        MemberDTO memberDTO = SecurityUtils.getSelfInfo();
        playerDTO.setMemberId(memberDTO.getId());

        playerService.save(toPlayerEntity(playerDTO));

        return new ApiResult<>(playerDTO);
    }
}
