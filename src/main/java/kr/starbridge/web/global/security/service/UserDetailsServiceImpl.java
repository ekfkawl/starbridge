package kr.starbridge.web.global.security.service;

import kr.starbridge.web.domain.member.dto.MemberMD5DTO;
import kr.starbridge.web.domain.member.entity.MemberEntity;
import kr.starbridge.web.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberService memberService;

    /**
     * 회원 로그인
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public MemberEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        /** Id -> MD5 */
        MemberMD5DTO memberMD5DTO = new MemberMD5DTO(username, "");

        MemberEntity memberEntity = memberService.getMember(memberMD5DTO.getMd5id()).orElseThrow(
                () -> new UsernameNotFoundException("user not authorized")
        );

        /** ROLE */
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(memberEntity.getAuth().name()));
        memberEntity.setAuthorities(grantedAuthorities);

        return memberEntity;
    }
}
