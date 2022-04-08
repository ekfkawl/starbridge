package kr.starbridge.web.global;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class Profile {

    @Value("${profile.name}")
    private String profileName;
}
