package kr.starbridge.web.global.aop;

import kr.starbridge.web.domain.forum.service.ForumBanService;
import kr.starbridge.web.global.common.enums.ExceptionEnum;
import kr.starbridge.web.global.common.response.ApiException;
import kr.starbridge.web.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 게시물/댓글 작성 전 차단된 아이피인지 확인
 */
@Aspect
@Component
@RequiredArgsConstructor
public class ForumBanAspect {
    private final ForumBanService forumBanService;

    @Pointcut("execution(* kr.starbridge.web.domain.forum.controller.ForumContentController.apiForumContent(..)) || execution(* kr.starbridge.web.domain.forum.controller.ForumCommentController.apiForumComment(..))")
    private void componentMethodPointcut() {}

    @Before("componentMethodPointcut()")
    public void beforeComponentMethod() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String remoteAddress = SecurityUtils.getRemoteAddress(request);

        boolean isBan = forumBanService.getBans().stream().anyMatch(p -> remoteAddress.equals(p.getIp()));
        if (isBan) {
            throw new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION.setMessage("you're banned"));
        }
    }
}
