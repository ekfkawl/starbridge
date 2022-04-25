package kr.starbridge.web.domain.forum.controller;

import kr.starbridge.web.domain.forum.dto.ForumCommentDTO;
import kr.starbridge.web.domain.forum.entity.ForumCommentEntity;
import kr.starbridge.web.domain.forum.service.ForumCommentService;
import kr.starbridge.web.global.common.enums.ExceptionEnum;
import kr.starbridge.web.global.common.response.ApiException;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.common.response.ValidationSequence;
import kr.starbridge.web.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

import static kr.starbridge.web.domain.forum.mapper.ForumCommentMapper.toForumCommentDTO;
import static kr.starbridge.web.domain.forum.mapper.ForumCommentMapper.toForumCommentEntity;

/**
 * 포럼 댓글 R/W 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class ForumCommentController extends ForumBaseController {

    private final ForumCommentService forumCommentService;

    /**
     * 댓글 작성/수정
     * @param forumCommentDTO
     * @return
     */
    @PostMapping("/api/comment")
    public ApiResult<ForumCommentDTO> apiForumComment(@Validated(ValidationSequence.class) @RequestBody ForumCommentDTO forumCommentDTO, HttpServletRequest request) {
        forumCommentDTO.setMember(SecurityUtils.getSelfInfo());
        forumCommentDTO.setIp(SecurityUtils.getRemoteAddress(request));

        forumCommentService.save(toForumCommentEntity(forumCommentDTO));

        return new ApiResult<>(forumCommentDTO, null, String.format("/forum/view/%s", forumCommentDTO.getContentSeq()));
    }

    /**
     * 댓글 조회 api
     * @param seq
     * @return
     */
    @GetMapping("/api/comment")
    public ApiResult<ForumCommentDTO> apiGetForumComment(@RequestParam String seq) {

        Optional<ForumCommentEntity> optionalForumCommentEntity = forumCommentService.getComment(Long.parseLong(seq));
        if (!optionalForumCommentEntity.isPresent()) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }

        return new ApiResult<>(toForumCommentDTO(optionalForumCommentEntity.get()));
    }

    /**
     * 댓글 삭제 api
     * @param forumCommentDTO
     * @return
     */
    @DeleteMapping("/api/comment")
    public ApiResult<ForumCommentDTO> apiDeleteForumComment(@Valid @RequestBody ForumCommentDTO forumCommentDTO) {
        forumCommentService.delete(toForumCommentEntity(forumCommentDTO));

        return new ApiResult<>(null, String.format("/forum/view/%s", forumCommentDTO.getContentSeq()));
    }
}