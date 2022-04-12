package kr.starbridge.web.domain.bridge.service;

import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import kr.starbridge.web.domain.bridge.entity.BattleTagId;
import kr.starbridge.web.domain.bridge.repository.BattleTagRepository;
import kr.starbridge.web.global.Regex;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-dev.properties, classpath:application.properties"})
class BattleTagServiceTest {

    @Autowired
    BattleTagService battleTagService;

    @Autowired
    BattleTagRepository battleTagRepository;

    @Test
    void findByTest() {
        BattleTagEntity battleTagEntities = battleTagRepository.findByIdMemberIdAndIdTag("b547861dcab956c7b782476e7e70af48", "aaaa#1111");
        System.out.println("battleTagEntities = " + battleTagEntities);
    }

    @Test
    void findBySortTest() {
        List<BattleTagEntity> battleTagEntities = battleTagRepository.findByIdMemberId("b547861dcab956c7b782476e7e70af48", Sort.by(Sort.Direction.ASC, "modifyDt"));
        System.out.println("battleTagEntities = " + battleTagEntities);
    }

    @Test
    void updateTest() {
        BattleTagEntity battleTagEntity = BattleTagEntity.builder()
                .id(new BattleTagId("b547861dcab956c7b782476e7e70af48", "test#1999"))
                .prevTag("test#9999")
                .memo("update test...")
                .isExport(true)
                .build();
        int update = battleTagRepository.update(battleTagEntity);
        System.out.println("update = " + update);
    }

    @Test
    void saveTest() {
        BattleTagEntity battleTagEntity = BattleTagEntity.builder()
                .id(new BattleTagId("b547861dcab956c7b782476e7e70af48", "test#5212"))
                .memo("save test...")
                .isExport(true)
                .build();

        battleTagRepository.save(battleTagEntity);
    }

    @Test
    void validBattleTagTest() {
        boolean regex = Pattern.matches(Regex.BATTLE_TAG, "bㅁcㅁa#123456");
        assertThat(regex).isTrue();

        regex = Pattern.matches(Regex.BATTLE_TAG, "한글#123456");
        assertThat(regex).isTrue();

        regex = Pattern.matches(Regex.BATTLE_TAG, "1111111abc1111111111111111111111#123456");
        assertThat(regex).isTrue();
    }
}