package kr.starbridge.web.domain.bridge.controller;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.bridge.dto.BattleTagDTO;
import kr.starbridge.web.domain.bridge.dto.CommonImportDTO;
import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import kr.starbridge.web.domain.bridge.enums.FunctionURIEnum;
import kr.starbridge.web.domain.bridge.mapper.BattleTagMapper;
import kr.starbridge.web.domain.bridge.service.BattleTagService;
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

import static kr.starbridge.web.domain.bridge.mapper.BattleTagMapper.toBattleTagDTO;
import static kr.starbridge.web.domain.bridge.mapper.BattleTagMapper.toBattleTagEntity;

/**
 * 배틀태그 블랙리스트 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class BridgeBattleTagController extends BridgeBaseController {

    private final BattleTagService battleTagService;

    /**
     * 등록/수정
     * @param battleTagDTO
     * @return
     */
    @PostMapping("/api/battle-tag")
    public ApiResult<BattleTagDTO> apiUpsertBattleTag(@Valid @RequestBody BattleTagDTO battleTagDTO) {
        MemberDTO memberDTO = SecurityUtils.getSelfInfo();
        battleTagDTO.getId().setMemberId(memberDTO.getId());

        BattleTagEntity battleTagEntity = battleTagService.upsertBattleTag(toBattleTagEntity(battleTagDTO));

        return new ApiResult(toBattleTagDTO(battleTagEntity));
    }

    /**
     * 조회
     * @param id
     * @return
     */
    @GetMapping("/api/battle-tag")
    public ApiResult<List<BattleTagDTO>> apiGetBattleTags(@RequestParam String id) {
        return new ApiResult<>(toBattleTagDTO(battleTagService.getBattleTagsEx(id)));
    }

    /**
     * 삭제
     * @param battleTagDTO
     * @return
     */
    @DeleteMapping("/api/battle-tag")
    public ApiResult<BattleTagDTO> apiDeleteBattleTag(@RequestBody BattleTagDTO battleTagDTO) {

        if (StringUtils.isNullOrEmpty(battleTagDTO.getId().getTag())) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }

        MemberDTO memberDTO = SecurityUtils.getSelfInfo();
        battleTagDTO.getId().setMemberId(memberDTO.getId());

        battleTagService.delete(BattleTagMapper.toBattleTagEntity(battleTagDTO));

        return new ApiResult<>(battleTagDTO);
    }

    /**
     * 블랙리스트 추출 대상 수정
     * @param battleTagDTO
     * @return
     */
    @PutMapping("/api/battle-tag-export")
    public ApiResult<List<BattleTagDTO>> apiUpdateBattleTagExport(@Valid @RequestBody BattleTagDTO battleTagDTO) {
        MemberDTO memberDTO = SecurityUtils.getSelfInfo();
        battleTagDTO.getId().setMemberId(memberDTO.getId());

        BattleTagEntity battleTagEntity = battleTagService.updateBattleTagExport(toBattleTagEntity(battleTagDTO));

        return new ApiResult(toBattleTagDTO(battleTagEntity));
    }

    /**
     * 블랙리스트 임포트
     * @param optionalCommonImportDTO
     * @return
     */
    @PostMapping("/api/battle-tag-import")
    public ApiResult<List<BattleTagDTO>> apiBattleTagImport(@RequestBody Optional<CommonImportDTO> optionalCommonImportDTO) {

        if (!optionalCommonImportDTO.isPresent()) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }
        CommonImportDTO commonImportDTO = optionalCommonImportDTO.get();

        MemberDTO memberDTO = SecurityUtils.getSelfInfo();

        List<BattleTagEntity> battleTagEntities = battleTagService.importTags(memberDTO.getId(), toBattleTagEntity(commonImportDTO));

        return new ApiResult<>(toBattleTagDTO(battleTagEntities), null, FunctionURIEnum.Url(FunctionURIEnum.URI_BLACKLIST_TAG));
    }
}

