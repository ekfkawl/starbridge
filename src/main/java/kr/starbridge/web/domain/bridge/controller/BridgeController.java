package kr.starbridge.web.domain.bridge.controller;

import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import kr.starbridge.web.domain.bridge.service.BattleTagService;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.global.aop.GlobalAdvice;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static kr.starbridge.web.domain.bridge.enums.FunctionURIEnum.URI_BLACKLIST_TAG;
import static kr.starbridge.web.domain.bridge.enums.FunctionURIEnum.URI_BLACKLIST_TAG_IMPORT;
import static kr.starbridge.web.domain.bridge.mapper.BattleTagMapper.toBattleTagDTO;

/**
 * bridge 도구 페이지 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class BridgeController extends BridgeBaseController {

    private final BattleTagService battleTagService;

    @GetMapping("/{function}")
    public ModelAndView bridge(@PathVariable(name = "function") String function,
                               @RequestParam(required = false, defaultValue = "") String pull,
                               Model model) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ModelAndView mv = new ModelAndView("bridge");

        /** 로그인 정보 */
        MemberDTO memberDTO = GlobalAdvice.getSelfInfo(model);

        /** 배틀태그 관련 view */
        if (function.contains(URI_BLACKLIST_TAG.getUri())) {
            List<BattleTagEntity> localBattleTagEntities = battleTagService.getBattleTagsEx(memberDTO.getId());
            model.addAttribute("battleTagDTOList", toBattleTagDTO(localBattleTagEntities));

            /** 배틀태그 import view */
            if (URI_BLACKLIST_TAG_IMPORT.getUri().equals(function)) {
                /** import 대상의 추출 허용 배틀태그 리스트 */
                List<BattleTagEntity> importBattleTagEntities = battleTagService.getRemoteExportTags(localBattleTagEntities, pull);
                model.addAttribute("importBattleTagDTOList", toBattleTagDTO(importBattleTagEntities));
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
