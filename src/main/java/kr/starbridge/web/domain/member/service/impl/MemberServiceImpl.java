package kr.starbridge.web.domain.member.service.impl;

import kr.starbridge.web.domain.bridge.entity.PlayerEntity;
import kr.starbridge.web.domain.bridge.entity.RoomRoleEntity;
import kr.starbridge.web.domain.bridge.service.PlayerService;
import kr.starbridge.web.domain.bridge.service.RoomRoleService;
import kr.starbridge.web.domain.member.entity.MemberEntity;
import kr.starbridge.web.domain.member.repository.MemberRepository;
import kr.starbridge.web.domain.member.service.MemberService;
import kr.starbridge.web.domain.member.service.RecaptchaService;
import kr.starbridge.web.global.Profile;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.utils.BeanSubUtils;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RoomRoleService roomRoleService;
    private final PlayerService playerService;
    private final RecaptchaService recaptchaService;
    private final Profile profile;

    @Override
    public ApiResult<Object> register(MemberEntity memberEntity, String recaptcha) throws ParseException {
        /** prod 환경일 경우 recaptcha 체크 */
        if ("prod".equals(profile.getProfileName())) {
            Float score = recaptchaService.verify(recaptcha);;
            if (score < 0.8) {
                return new ApiResult<>("봇 의심되어 가입이 중단되었습니다");
            }
        }
        /** 프로필 이미지 랜덤 */
        int x = (int) (Math.random() * 20);
        memberEntity.setImg(String.format("%s%d.png","/image/default/", x));

        save(memberEntity);

        /** 브릿지 기본 설정 값 셋팅 */
        roomRoleService.save(RoomRoleEntity.builder()
                .memberId(memberEntity.getId())
                .build());

        playerService.save(PlayerEntity.builder()
                .memberId(memberEntity.getId())
                .enablePingColor(true)
                .enableDisProtect(true)
                .enableOwner(true)
                .build());

        return new ApiResult<>("계정이 등록되었습니다", "/");
    }

    @Transactional
    @Override
    public Optional<MemberEntity> getMember(String id) {
        return memberRepository.findById(id);
    }

    @Transactional
    @Override
    public long getMemberCount() {
        return memberRepository.count();
    }

    @Transactional
    @Override
    public boolean isExistsId(String id) {
        return memberRepository.existsById(id);
    }

    @Transactional
    @Override
    public boolean isExistsName(String name) {
        return memberRepository.existsByName(name);
    }

    @Transactional
    @Override
    public ApiResult<Object> modify(MemberEntity memberEntity) {
        save(memberEntity);

        return new ApiResult<>("변경사항이 저장되었습니다\n다시 로그인 해주세요", "/logout");
    }

    @Transactional
    @Override
    public boolean isMemberForExternal(String id, String pw) {
        return memberRepository.countByIdAndPw(id, pw) > 0;
    }

    @Transactional
    @Override
    public MemberEntity save(MemberEntity memberEntity) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(memberEntity.getId());
        if (optionalMemberEntity.isPresent()) {
            MemberEntity currentMemberEntity = optionalMemberEntity.get();
            BeanUtils.copyProperties(memberEntity, currentMemberEntity, BeanSubUtils.getNullPropertyNames(memberEntity));

            return currentMemberEntity;
        }else {
            return memberRepository.save(memberEntity);
        }
    }

}
