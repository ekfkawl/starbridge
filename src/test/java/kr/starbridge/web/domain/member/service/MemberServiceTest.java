package kr.starbridge.web.domain.member.service;

import com.nhncorp.lucy.security.xss.XssPreventer;
import kr.starbridge.web.domain.member.entity.MemberEntity;
import kr.starbridge.web.domain.member.repository.MemberRepository;
import kr.starbridge.web.domain.member.service.impl.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-dev.properties, classpath:application.properties"})
class MemberServiceTest {

    @Autowired
    MemberServiceImpl memberService;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    @Test
    void pwEncodeTest() {
        System.out.println(passwordEncoder.encode("123123"));

    }

    @Test
    void pwEncodeMatcheTest() {
        System.out.println("matche result : " + passwordEncoder.matches("123123", "$2a$10$Vs/415CDjZCvo0qMc7StPujR43Gem9lzRZi20uIXPmxNwiJioJ/tu"));

    }

    @Test
    void imgGenerateTest() {

        List<MemberEntity> memberEntities = memberRepository.findAll();
        for (MemberEntity memberEntity : memberEntities) {
            int x = (int) (Math.random() * 20);
            memberEntity.setImg(String.format("%s%d.png","/image/default/", x));
        }
//        memberRepository.saveAll(memberEntities);
    }
}