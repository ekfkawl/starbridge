package kr.starbridge.web.domain.bridge.validator;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.bridge.dto.BattleTagDTO;
import kr.starbridge.web.domain.bridge.dto.IpDTO;
import kr.starbridge.web.domain.bridge.service.BattleTagService;
import kr.starbridge.web.domain.bridge.service.IpService;
import kr.starbridge.web.domain.bridge.validator.annotation.BridgeNotExists;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.global.Regex;
import kr.starbridge.web.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static kr.starbridge.web.global.utils.EscapeUtils.unescape;

@RequiredArgsConstructor
public class BridgeNotExistsValidator implements ConstraintValidator<BridgeNotExists, Object>  {

    private final BattleTagService battleTagService;
    private final IpService ipService;

    private Class<?> reference;

    public final static String VIOLATION_NOT_BATTLE_TAG = "올바른 배틀태그 형식이 아닙니다";
    public final static String VIOLATION_EXISTS_BATTLE_TAG = "이미 존재하는 배틀태그 입니다";
    public final static String VIOLATION_NOT_MD5_HASH = "올바른 해시 형식이 아닙니다";
    public final static String VIOLATION_EXISTS_MD5_HASH = "이미 존재하는 해시 입니다";


    @Override
    public void initialize(BridgeNotExists constraintAnnotation) {
        reference = constraintAnnotation.reference();
    }

    @Override
    public boolean isValid(Object v, ConstraintValidatorContext context) {
        MemberDTO memberDTO = SecurityUtils.getSelfInfo();

        /** 배틀태그 */
        if (reference == BattleTagDTO.class) {
            BattleTagDTO value = (BattleTagDTO) v;

            /** 배틀태그 유효성 체크 */
            String tag = unescape(value.getId().getTag());
            if (StringUtils.isNullOrEmpty(tag) || !tag.matches(Regex.BATTLE_TAG)) {
                addConstraintViolation(context, VIOLATION_NOT_BATTLE_TAG);
                return false;
            }

            /** 기존에 등록된 배틀태그 인지 */
            boolean isExistsTag = battleTagService.isExistsTag(memberDTO.getId(), value.getId().getTag());
            if (isExistsTag && !value.getId().getTag().equals(value.getPrevTag())) {
                addConstraintViolation(context, VIOLATION_EXISTS_BATTLE_TAG);
                return false;
            }
        }
        
        /** 아이피해시 */
        if (reference == IpDTO.class) {
            IpDTO value = (IpDTO) v;
            
            /** 아이피 해시 유효성 체크 */
            String hash = value.getId().getHash();
            if (StringUtils.isNullOrEmpty(hash) || !hash.matches(Regex.MD5_HASH)) {
                addConstraintViolation(context, VIOLATION_NOT_MD5_HASH);
                return false;
            }

            /** 기존에 등록된 아이피 해시 인지 */
            boolean isExistsTag = ipService.isExistsHash(memberDTO.getId(), value.getId().getHash());
            if (isExistsTag && !value.getId().getHash().equals(value.getPrevHash())) {
                addConstraintViolation(context, VIOLATION_EXISTS_MD5_HASH);
                return false;
            }
        }

        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context, String errorMessage) {
        /** 기본 메시지 비활성화 */
        context.disableDefaultConstraintViolation();
        /** 새로운 메시지 추가 */
        context.buildConstraintViolationWithTemplate(errorMessage)
                .addConstraintViolation();
    }
}
