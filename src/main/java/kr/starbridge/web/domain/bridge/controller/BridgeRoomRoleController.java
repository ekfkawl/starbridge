package kr.starbridge.web.domain.bridge.controller;

import kr.starbridge.web.domain.bridge.dto.RoomRoleDTO;
import kr.starbridge.web.domain.bridge.service.RoomRoleService;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static kr.starbridge.web.domain.bridge.mapper.RoomRoleMapper.toRoomRoleEntity;

/**
 * 방 입장 조건 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class BridgeRoomRoleController extends BridgeBaseController {

    private final RoomRoleService roomRoleService;

    @PostMapping("/api/room-role")
    public ApiResult<RoomRoleDTO> apiUpsertRole(@RequestBody RoomRoleDTO roomRoleDTO) {

        MemberDTO memberDTO = SecurityUtils.getSelfInfo();
        roomRoleDTO.setMemberId(memberDTO.getId());

        roomRoleService.save(toRoomRoleEntity(roomRoleDTO));

        return new ApiResult<>(roomRoleDTO);
    }
}
