package kr.starbridge.web.domain.bridge.controller;

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

import static kr.starbridge.web.domain.bridge.enums.FunctionURIEnum.URI_BLACKLIST_TAG;
import static kr.starbridge.web.domain.bridge.enums.FunctionURIEnum.URI_BLACKLIST_TAG_EXPORT;

/**
 * bridge 도구 페이지 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class BridgeController {

    private final BattleTagService battleTagService;

    @GetMapping("/bridge/{function}")
    public ModelAndView bridge(@PathVariable(name = "function") String function,
                               @RequestParam(required = false, defaultValue = "") String ref,
                               Model model) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        ModelAndView mv = new ModelAndView("bridge");

        /** 로그인 정보 */
        MemberDTO memberDTO = GlobalAdvice.getSelfInfo(model);

        if (function.contains(URI_BLACKLIST_TAG.getUri())) {
            model.addAttribute("battleTagDTOList", battleTagService.getBattleTagsEx(memberDTO.getId()).getData());
        }

        mv.addObject("uri", "/bridge/" + function);

        return mv;
    }

}
