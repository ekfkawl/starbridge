package kr.starbridge.web.domain.forum.controller;

import kr.starbridge.web.domain.forum.dto.ForumContentDTO;
import kr.starbridge.web.domain.forum.entity.ForumContentEntity;
import kr.starbridge.web.domain.forum.service.ForumContentService;
import kr.starbridge.web.global.common.enums.ExceptionEnum;
import kr.starbridge.web.global.common.response.ApiException;
import kr.starbridge.web.global.common.response.ApiResult;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static com.mysql.cj.util.StringUtils.isNullOrEmpty;
import static kr.starbridge.web.domain.forum.mapper.ForumContentMapper.toForumContentDTO;

/**
 * 포럼 게시물 리스트 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class ForumIntroController {

    private final ForumContentService forumContentService;

    @GetMapping("/forum")
    public ModelAndView forum(@RequestParam(required = false, defaultValue = "") String search) {

        ModelAndView mv = new ModelAndView("forum");

        List<ForumContentEntity> forumContentEntities = forumContentService.getContents(0, search);

        mv.addObject("forumContentDTOList", toForumContentDTO(forumContentEntities));

        return mv;
    }

    @GetMapping("/forum/more")
    public ApiResult<List<ForumContentDTO>> forumMore(@RequestParam String page, @RequestParam(required = false, defaultValue = "") String search) {

        if (isNullOrEmpty(page) && !StringUtils.isNumeric(page)) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }

        List<ForumContentEntity> forumContentEntities = forumContentService.getContents(Integer.parseInt(page), search);

        return new ApiResult<>(toForumContentDTO(forumContentEntities));
    }
}
