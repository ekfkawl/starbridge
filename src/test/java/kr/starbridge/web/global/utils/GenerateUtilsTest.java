package kr.starbridge.web.global.utils;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static org.mockito.Mockito.mockStatic;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-dev.properties, classpath:application.properties"})
class GenerateUtilsTest {

    private static MockedStatic<GenerateUtils> mGenerateUtils;

    @BeforeClass
    public static void beforeClass() {
        mGenerateUtils = mockStatic(GenerateUtils.class);
    }

    @AfterClass
    public static void afterClass() {
        mGenerateUtils.close();
    }

    @Test
    void toMD5Test() {
        String test = GenerateUtils.StrToMD5("test");
        System.out.println("test = " + test);
    }
}