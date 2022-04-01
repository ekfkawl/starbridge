package kr.starbridge.web.domain.member.controller;

import kr.starbridge.web.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor

/**
 * 인덱스 페이지 컨트롤러
 */
public class IndexController {

    private final MemberService memberService;

    @GetMapping("/")
    public ModelAndView index() {

        ModelAndView mv = new ModelAndView("index");
        /** 회원 수 */
        mv.addObject("memberCount", memberService.getMemberCount());

        return mv;
    }
}