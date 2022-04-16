package kr.starbridge.web.domain.bridge.controller;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.bridge.dto.CommonImportDTO;
import kr.starbridge.web.domain.bridge.dto.RoomFilterDTO;
import kr.starbridge.web.domain.bridge.entity.RoomFilterEntity;
import kr.starbridge.web.domain.bridge.enums.FunctionURIEnum;
import kr.starbridge.web.domain.bridge.service.RoomFilterService;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.global.common.enums.ExceptionEnum;
import kr.starbridge.web.global.common.response.ApiException;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static kr.starbridge.web.domain.bridge.mapper.RoomFilterMapper.toRoomFilterDTO;
import static kr.starbridge.web.domain.bridge.mapper.RoomFilterMapper.toRoomFilterEntity;

/**
 * 방 필터링 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class BridgeRoomFilterController extends BridgeBaseController {

    private final RoomFilterService roomFilterService;

    /**
     * 등록/수정
     * @param roomFilterDTO
     * @return
     */
    @PostMapping("/api/room-filter")
    public ApiResult<RoomFilterDTO> apiUpsertKeyword(@Valid @RequestBody RoomFilterDTO roomFilterDTO) {
        MemberDTO memberDTO = SecurityUtils.getSelfInfo();
        roomFilterDTO.getId().setMemberId(memberDTO.getId());

        RoomFilterEntity roomFilterEntity = roomFilterService.upsertKeyword(toRoomFilterEntity(roomFilterDTO));

        return new ApiResult(toRoomFilterDTO(roomFilterEntity));
    }

    /**
     * 조회
     * @param id
     * @return
     */
    @GetMapping("/api/room-filter")
    public ApiResult<List<RoomFilterDTO>> apiGetKeywords(@RequestParam String id) {
        return new ApiResult<>(toRoomFilterDTO(roomFilterService.getKeywordsEx(id)));
    }

    /**
     * 삭제
     * @param roomFilterDTO
     * @return
     */
    @DeleteMapping("/api/room-filter")
    public ApiResult<RoomFilterDTO> apiDeleteKeyword(@RequestBody RoomFilterDTO roomFilterDTO) {

        if (StringUtils.isNullOrEmpty(roomFilterDTO.getId().getKeyword())) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }

        MemberDTO memberDTO = SecurityUtils.getSelfInfo();
        roomFilterDTO.getId().setMemberId(memberDTO.getId());

        roomFilterService.delete(toRoomFilterEntity(roomFilterDTO));

        return new ApiResult<>(roomFilterDTO);
    }

    /**
     * 필터링 키워드 추출 대상 수정
     * @param roomFilterDTO
     * @return
     */
    @PutMapping("/api/room-filter-export")
    public ApiResult<List<RoomFilterDTO>> apiUpdateKeywordExport(@Valid @RequestBody RoomFilterDTO roomFilterDTO) {
        MemberDTO memberDTO = SecurityUtils.getSelfInfo();
        roomFilterDTO.getId().setMemberId(memberDTO.getId());

        RoomFilterEntity roomFilterEntity = roomFilterService.updateKeywordExport(toRoomFilterEntity(roomFilterDTO));

        return new ApiResult(toRoomFilterDTO(roomFilterEntity));
    }

    /**
     * 필터링 키워드 임포트
     * @param optionalCommonImportDTO
     * @return
     */
    @PostMapping("/api/room-filter-import")
    public ApiResult<List<RoomFilterDTO>> apiKeywordImport(@RequestBody Optional<CommonImportDTO> optionalCommonImportDTO) {

        if (!optionalCommonImportDTO.isPresent()) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }
        CommonImportDTO commonImportDTO = optionalCommonImportDTO.get();

        MemberDTO memberDTO = SecurityUtils.getSelfInfo();

        List<RoomFilterEntity> roomFilterEntities = roomFilterService.importKeywords(memberDTO.getId(), toRoomFilterEntity(commonImportDTO));

        return new ApiResult<>(toRoomFilterDTO(roomFilterEntities), null, FunctionURIEnum.Url(FunctionURIEnum.URI_ROOM_FILTER));
    }
}
