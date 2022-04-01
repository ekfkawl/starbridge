package kr.starbridge.web.global;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class Profile {

    @Value("${profile.name}")
    private String profileName;
}
