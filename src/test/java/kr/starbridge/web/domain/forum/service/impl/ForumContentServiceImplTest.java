package kr.starbridge.web.domain.forum.service.impl;

import kr.starbridge.web.domain.forum.entity.ForumContentEntity;
import kr.starbridge.web.domain.forum.repository.ForumContentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-dev.properties, classpath:application.properties"})
class ForumContentServiceImplTest {

    @Autowired
    ForumContentRepository forumContentRepository;

    @Test
    void findAllTest() {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "createDt"));
        List<ForumContentEntity> all = forumContentRepository.findAllByTitleContainingOrContentContaining("1", "1", pageable);
        System.out.println("all = " + all);
    }

}