package kr.starbridge.web.domain.forum.controller;

import kr.starbridge.web.domain.forum.dto.ForumCommentDTO;
import kr.starbridge.web.domain.forum.dto.ForumContentDTO;
import kr.starbridge.web.domain.forum.entity.ForumContentEntity;
import kr.starbridge.web.domain.forum.service.ForumContentService;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.common.response.ValidationSequence;
import kr.starbridge.web.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Optional;

import static com.mysql.cj.util.StringUtils.isNullOrEmpty;
import static kr.starbridge.web.domain.forum.mapper.ForumContentMapper.toForumContentDTO;
import static kr.starbridge.web.domain.forum.mapper.ForumContentMapper.toForumContentEntity;

/**
 * 포럼 게시물 R/W 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class ForumContentController extends ForumBaseController {

    private final ForumContentService forumContentService;

    /**
     * 게시물 작성/수정 view
     * @param seq
     * @return
     */
    @GetMapping(value = {"/content", "/content/{seq}"})
    public ModelAndView content(@PathVariable(required = false) String seq) {

        ModelAndView mv = new ModelAndView("forum/content_upsert");
        ModelAndView mvErr = new ModelAndView("redirect:/forum");

        /** 게시물 수정 */
        if (!isNullOrEmpty(seq) && StringUtils.isNumeric(seq)) {
            HashMap<String, Object> contentWriter = forumContentService.getContentWriter(Long.parseLong(seq));
            if (!(boolean) contentWriter.get("isLocal")) {
                return mvErr;
            }
            mv.addObject("forumContentDTO", toForumContentDTO((ForumContentEntity) contentWriter.get("entity")));
        }

        return mv;
    }

    /**
     * 게시물 작성/수정 api
     * @param forumContentDTO
     * @return
     */
    @PostMapping("/api/content")
    public ApiResult<ForumContentDTO> apiForumContent(@Validated(ValidationSequence.class) @RequestBody ForumContentDTO forumContentDTO) {
        forumContentDTO.setMember(SecurityUtils.getSelfInfo());

        ForumContentEntity forumContentEntity = forumContentService.save(toForumContentEntity(forumContentDTO));

        return new ApiResult(toForumContentDTO(forumContentEntity), null, "/forum");
    }

    /**
     * 게시물 삭제 api
     * @param forumContentDTO
     * @return
     */
    @DeleteMapping("/api/content")
    public ApiResult<ForumCommentDTO> apiDeleteForumContent(@Valid @RequestBody ForumContentDTO forumContentDTO) {
        forumContentService.delete(toForumContentEntity(forumContentDTO));

        return new ApiResult<>(null, "/forum");
    }

    /**
     * 게시물 view
     * @param seq
     * @return
     */
    @GetMapping("/view/{seq}")
    public ModelAndView contentView(@PathVariable(required = false) String seq) {
        ModelAndView mv = new ModelAndView("forum/content_view");
        ModelAndView mvErr = new ModelAndView("redirect:/forum");

        /** 파라미터 유효성 체크 */
        if (isNullOrEmpty(seq) || !StringUtils.isNumeric(seq)) {
            return mvErr;
        }

        long contentSeq = Long.parseLong(seq);
        Optional<ForumContentEntity> optionalForumContentEntity = forumContentService.getContent(contentSeq);
        if (!optionalForumContentEntity.isPresent()) {
            return mvErr;
        }

        forumContentService.incViewCount(contentSeq);

        ForumContentEntity forumContentEntity = optionalForumContentEntity.get();
        mv.addObject("forumContentDTO", toForumContentDTO(forumContentEntity));

        return mv;
    }
}
