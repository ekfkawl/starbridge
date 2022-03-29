package kr.starbridge.web.domain.member.service;

import kr.starbridge.web.domain.member.entity.MemberEntity;
import kr.starbridge.web.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<MemberEntity> findAll() {
        return memberRepository.findAll();
    }
}
