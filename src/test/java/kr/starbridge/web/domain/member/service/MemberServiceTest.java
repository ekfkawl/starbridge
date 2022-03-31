package kr.starbridge.web.domain.member.service;

import kr.starbridge.web.domain.member.entity.MemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-dev.properties, classpath:application.properties"})
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void findAllTest() {
        List<MemberEntity> all = memberService.findAll();
        System.out.println("all = " + all);
    }
}