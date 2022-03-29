package kr.starbridge.web.global.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpSession;

@ControllerAdvice("kr.starbridge.web.domain")
public class GlobalBaseController {

    @Value("${session.user}")
    private String user;

    public void test(HttpSession session) {

    }
}
