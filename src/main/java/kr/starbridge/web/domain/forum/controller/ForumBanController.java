package kr.starbridge.web.domain.forum.controller;

import kr.starbridge.web.domain.forum.dto.ForumBanDTO;
import kr.starbridge.web.domain.forum.mapper.ForumBanMapper;
import kr.starbridge.web.domain.forum.service.ForumBanService;
import kr.starbridge.web.global.common.response.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ForumBanController extends ForumBaseController {
    private final ForumBanService forumBanService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/api/ban")
    public ApiResult<Object> apiForumBan(@RequestBody ForumBanDTO forumBanDTO) {

        forumBanService.addBan(ForumBanMapper.toForumBanEntity(forumBanDTO));

        return new ApiResult<>();
    }
}
