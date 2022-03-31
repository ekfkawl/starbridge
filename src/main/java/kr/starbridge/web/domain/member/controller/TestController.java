package kr.starbridge.web.domain.member.controller;

import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.global.common.response.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    @GetMapping("/test")
    public ApiResult<Object> test() {

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId("test");

//        throw new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION);

        return new ApiResult<>(null, "view",false);

    }
}