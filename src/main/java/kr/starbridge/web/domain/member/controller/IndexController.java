package kr.starbridge.web.domain.member.controller;

import kr.starbridge.web.domain.forum.service.ForumContentService;
import kr.starbridge.web.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.NumberFormat;

/**
 * 인덱스 페이지 컨트롤러
 */
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final MemberService memberService;
    private final ForumContentService forumContentService;

    @GetMapping("/")
    public ModelAndView index() {

        ModelAndView mv = new ModelAndView("index");
        /** 회원 수 */
        NumberFormat numberFormat = NumberFormat.getInstance();
        mv.addObject("memberCount", numberFormat.format(memberService.getMemberCount()));
        mv.addObject("forumContentCount", numberFormat.format(forumContentService.getForumContentCount()));

        return mv;
    }
}