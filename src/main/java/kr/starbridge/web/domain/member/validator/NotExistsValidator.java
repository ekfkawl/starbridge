package kr.starbridge.web.domain.member.validator;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.member.dto.MemberModifyDTO;
import kr.starbridge.web.domain.member.dto.MemberRegisterDTO;
import kr.starbridge.web.domain.member.service.MemberService;
import kr.starbridge.web.domain.member.validator.annotation.NotExists;
import kr.starbridge.web.global.common.enums.ExceptionEnum;
import kr.starbridge.web.global.common.response.ApiException;
import kr.starbridge.web.global.common.response.ApiResult;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class NotExistsValidator implements ConstraintValidator<NotExists, Object>  {

    private final MemberService memberService;

    private Class<?> reference;

    public final static String VIOLATION_EXISTS_ID = "이미 존재하는 아이디 입니다";
    public final static String VIOLATION_EXISTS_NAME = "이미 존재하는 닉네임 입니다";
    public final static String VIOLATION_NOT_EQUALS_PW = "비밀번호와 비밀번호 확인란이 동일하지 않습니다";

    @Override
    public void initialize(NotExists constraintAnnotation) {
        reference = constraintAnnotation.reference();
    }

    @Override
    public boolean isValid(Object v, ConstraintValidatorContext context) {

        /** 회원가입 */
        if (reference == MemberRegisterDTO.class) {
            MemberRegisterDTO value = (MemberRegisterDTO) v;
            if (!value.getPw1().equals(value.getPw2())) {
                addConstraintViolation(context, VIOLATION_NOT_EQUALS_PW);
                return false;
            }
            if (memberService.isExistsId(value.getMd5id())) {
                addConstraintViolation(context, VIOLATION_EXISTS_ID);
                return false;
            }
            if (memberService.isExistsName(value.getName())) {
                addConstraintViolation(context, VIOLATION_EXISTS_NAME);
                return false;
            }
        }

        /** 회원정보 수정 */
        if (reference == MemberModifyDTO.class) {
            MemberModifyDTO value = (MemberModifyDTO) v;
            if (StringUtils.isNullOrEmpty(value.getPrevName())) {
                throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
            }
            if (!value.getPrevName().equals(value.getName()) && memberService.isExistsName(value.getName())) {
                addConstraintViolation(context, VIOLATION_EXISTS_NAME);
                return false;
            }
            if (!value.getPw1().equals(value.getPw2())) {
                addConstraintViolation(context, VIOLATION_NOT_EQUALS_PW);
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
