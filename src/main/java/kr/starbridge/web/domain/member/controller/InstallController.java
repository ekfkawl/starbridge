package kr.starbridge.web.domain.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 인스톨 페이지 컨트롤러
 */
@RestController
public class InstallController {

    @GetMapping("/install")
    public ModelAndView install() {

        ModelAndView mv = new ModelAndView("install");

        return mv;
    }
}
