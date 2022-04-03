package kr.starbridge.web.global.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 규칙 설정
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/", "/css/**", "/image/**", "/js/**", "/json/**", "/uploaded/**").permitAll()
                    .antMatchers("/modify").hasRole("USER")
                .and()
                    .formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/siginin")
                    .usernameParameter("md5id")
                    .passwordParameter("md5pw")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                    .csrf().disable();
    }

    /**
     * 로그인 인증 처리 메소드
     * @param auth
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    }
}