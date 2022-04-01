package kr.starbridge.web.domain.member.service;

import kr.starbridge.web.domain.member.dto.MemberRegisterDTO;
import kr.starbridge.web.domain.member.entity.MemberEntity;
import kr.starbridge.web.domain.member.repository.MemberRepository;
import kr.starbridge.web.global.Profile;
import kr.starbridge.web.global.common.response.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final Profile profile;
    private final MemberRepository memberRepository;
    private final RecaptchaService recaptchaService;

    /**
     * 회원가입
     * @param registerDTO
     * @return
     * @throws IOException
     */
    public ApiResult<Object> register(MemberRegisterDTO registerDTO) throws IOException {

        /** prod 환경일 경우 recaptcha 체크 */
        if ("prod".equals(profile.getProfileName())) {
            boolean isVerify = recaptchaService.verify(registerDTO.getGRecaptchaResponse());
            if (!isVerify) {
                return new ApiResult<>("reCAPTCHA 스팸방지 기능을 체크해 주십시오");
            }
        }

        if (!registerDTO.getPw1().equals(registerDTO.getPw2())) {
            return new ApiResult<>("비밀번호와 비밀번호 확인란이 동일하지 않습니다");
        }

        if (isExistId(registerDTO.getMd5id())) {
            return new ApiResult<>("이미 존재하는 아이디 입니다");
        }

        if (isExistName(registerDTO.getName())) {
            return new ApiResult<>("이미 존재하는 닉네임 입니다");
        }

        MemberEntity memberEntity = MemberEntity.builder()
                .id(registerDTO.getMd5id())
                .pw(registerDTO.getMd5pw())
                .name(registerDTO.getName())
                .build();
        memberRepository.save(memberEntity);

        return new ApiResult<>("계정이 등록되었습니다", "/");
    }

    /**
     * 아이디 존재 여부 체크
     * @param id
     * @return
     */
    public boolean isExistId(String id) {
        return memberRepository.existsById(id);
    }

    /**
     * 닉네임 존재 여부 체크
     * @param name
     * @return
     */
    public boolean isExistName(String name) {
        return memberRepository.existsByName(name);
    }

    public List<MemberEntity> findAll() {
        return memberRepository.findAll();
    }
}
