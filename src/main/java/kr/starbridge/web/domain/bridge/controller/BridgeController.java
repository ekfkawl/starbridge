package kr.starbridge.web.domain.bridge.controller;

import kr.starbridge.web.domain.bridge.enums.FunctionURIEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * bridge 도구 페이지 컨트롤러
 */
@RestController
public class BridgeController {

    @GetMapping("/bridge/{function}")
    public ModelAndView bridge(@PathVariable(name = "function") String function,
                               @RequestParam(required = false, defaultValue = "") String ref) {

        ModelAndView mv = new ModelAndView();

        switch(function) {
            case "blacklist-tag":
                break;
            case "blacklist-ip":
                break;
            case "room-filter":
                break;
            case "room-role":
                break;
            case "player":


        }

        mv.addObject("f", function);
        return mv;
    }

}
