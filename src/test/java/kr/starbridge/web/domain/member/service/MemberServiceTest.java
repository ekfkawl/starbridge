package kr.starbridge.web.domain.member.service;

import kr.starbridge.web.domain.member.entity.MemberEntity;
import kr.starbridge.web.domain.member.repository.MemberRepository;
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

    @Autowired
    MemberRepository memberRepository;

    @Test
    void findAllTest() {
        List<MemberEntity> all = memberRepository.findAll();
        System.out.println("all = " + all);
    }

    @Test
    void findById() {
        boolean existId = memberRepository.existsById("47a4f7c325931bd344fbe1ca7cd1709");
        System.out.println("existId = " + existId);
    }

    @Test
    void countTest() {
        long count = memberRepository.count();
        System.out.println("count = " + count);
    }
}