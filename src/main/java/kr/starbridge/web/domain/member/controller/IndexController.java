package kr.starbridge.web.domain.member.controller;

import kr.starbridge.web.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 인덱스 페이지 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final MemberRepository memberRepository;

    @GetMapping("/")
    public ModelAndView index() {

        ModelAndView mv = new ModelAndView("index");
        /** 회원 수 */
        mv.addObject("memberCount", memberRepository.count());

        return mv;
    }
}