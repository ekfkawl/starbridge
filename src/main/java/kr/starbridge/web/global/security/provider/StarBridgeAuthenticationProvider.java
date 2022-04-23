package kr.starbridge.web.global.security.provider;

import kr.starbridge.web.domain.member.entity.MemberEntity;
import kr.starbridge.web.global.utils.GenerateUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class StarBridgeAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public StarBridgeAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String loginId = authentication.getName();
        /** PW: MD5 */
        String password = GenerateUtils.StrToMD5(authentication.getCredentials().toString());

        MemberEntity memberEntity = (MemberEntity)userDetailsService.loadUserByUsername(loginId);

        if (!password.equals(memberEntity.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
//        if (!passwordEncoder.matches(password, memberEntity.getPassword())) {
//            throw new BadCredentialsException("Invalid password");
//        }
        return new UsernamePasswordAuthenticationToken(memberEntity, memberEntity.getPassword(), memberEntity.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
