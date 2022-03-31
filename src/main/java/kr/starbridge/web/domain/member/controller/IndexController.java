package kr.starbridge.web.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor

/**
 * 인덱스 페이지 컨트롤러
 */
public class IndexController {

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }
}