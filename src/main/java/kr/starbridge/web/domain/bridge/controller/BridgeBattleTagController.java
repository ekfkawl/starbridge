package kr.starbridge.web.domain.bridge.controller;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.bridge.dto.BattleTagDTO;
import kr.starbridge.web.domain.bridge.entity.BattleTagId;
import kr.starbridge.web.domain.bridge.service.BattleTagService;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.global.Regex;
import kr.starbridge.web.global.aop.GlobalAdvice;
import kr.starbridge.web.global.common.enums.ExceptionEnum;
import kr.starbridge.web.global.common.response.ApiException;
import kr.starbridge.web.global.common.response.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
     * @param model
     * @return
     */
    @PostMapping("/api/battle-tag")
    public ApiResult<Object> apiUpsertBattleTag(@RequestBody BattleTagDTO battleTagDTO, Model model) {

        String tag = battleTagDTO.getId().getTag();
        if (StringUtils.isNullOrEmpty(tag) || !tag.matches(Regex.BATTLE_TAG)) {
            return new ApiResult<>("올바른 배틀태그 형식이 아닙니다");
        }

        MemberDTO memberDTO = GlobalAdvice.getSelfInfo(model);
        battleTagDTO.getId().setMemberId(memberDTO.getId());

        return battleTagService.upsertBattleTag(battleTagDTO);
    }

    /**
     * 조회
     * @param id
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    @GetMapping("/api/battle-tag")
    public ApiResult<List<BattleTagDTO>> apiGetBattleTags(@RequestParam String id) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return battleTagService.getBattleTagsEx(id);
    }

    /**
     * 삭제
     * @param battleTagId
     * @param model
     * @return
     */
    @DeleteMapping("/api/battle-tag")
    public ApiResult<Object> apiDeleteBattleTag(@RequestBody BattleTagId battleTagId, Model model) {

        if (StringUtils.isNullOrEmpty(battleTagId.getTag())) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }

        MemberDTO memberDTO = GlobalAdvice.getSelfInfo(model);
        battleTagId.setMemberId(memberDTO.getId());

        battleTagService.delete(battleTagId);

        return new ApiResult<>(battleTagId);
    }

    /**
     * 블랙리스트 추출 대상 수정
     * @param battleTagDTO
     * @param model
     * @return
     */
    @PutMapping("/api/battle-tag-export")
    public ApiResult<Object> apiUpdateBattleTagExport(@RequestBody BattleTagDTO battleTagDTO, Model model) {

        String tag = battleTagDTO.getId().getTag();
        if (StringUtils.isNullOrEmpty(tag) || !tag.matches(Regex.BATTLE_TAG)) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }

        MemberDTO memberDTO = GlobalAdvice.getSelfInfo(model);
        battleTagDTO.getId().setMemberId(memberDTO.getId());

        return battleTagService.updateBattleTagExport(battleTagDTO);
    }
}

