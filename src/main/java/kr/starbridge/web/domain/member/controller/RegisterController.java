package kr.starbridge.web.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor

/**
 * 회원가입
 */
public class RegisterController {

    /**
     * 회원가입 뷰로 이동
     * @return
     */
    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("register");
    }
//
//    @PostMapping("/api/register")
//    public

}
