package kr.starbridge.web.domain.forum.validator;

import kr.starbridge.web.domain.forum.dto.ForumCommentDTO;
import kr.starbridge.web.domain.forum.dto.ForumContentDTO;
import kr.starbridge.web.domain.forum.entity.ForumCommentEntity;
import kr.starbridge.web.domain.forum.service.ForumCommentService;
import kr.starbridge.web.domain.forum.service.ForumContentService;
import kr.starbridge.web.domain.forum.validator.annotation.ForumRequest;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashMap;
import java.util.Optional;

@RequiredArgsConstructor
public class ForumRequestValidator implements ConstraintValidator<ForumRequest, Object> {

    private final ForumContentService forumContentService;
    private final ForumCommentService forumCommentService;

    private Class<?> clazz;

    @Override
    public void initialize(ForumRequest constraintAnnotation) {
        clazz = constraintAnnotation.clazz();
    }

    @Override
    public boolean isValid(Object v, ConstraintValidatorContext context) {
        /** 게시물 수정/삭제시 seq 검증 */
        if (clazz == ForumContentDTO.class) {
            ForumContentDTO value = (ForumContentDTO) v;

            HashMap<String, Object> contentWriter = forumContentService.getContentWriter(value.getSeq());
            if (!(boolean) contentWriter.get("isLocal")) {
                addConstraintViolation(context, "not writer");
                return false;
            }
        }
        /** 댓글 */
        else if (clazz == ForumCommentDTO.class) {
            ForumCommentDTO value = (ForumCommentDTO) v;

            /** 댓글 수정/삭제시 seq 검증 */
            HashMap<String, Object> contentWriter = forumCommentService.getCommentWriter(value.getSeq());
            if (!(boolean) contentWriter.get("isLocal")) {
                addConstraintViolation(context, "not writer");
                return false;
            }

            /** parent_comment seq request 검증 */
            if (value.getParentComment() != -1) {
                Optional<ForumCommentEntity> optionalForumParentCommentEntity = forumCommentService.getComment(value.getParentComment());
                if (!optionalForumParentCommentEntity.isPresent()) {
                    addConstraintViolation(context, "bad parent");
                    return false;
                }
                ForumCommentEntity forumParentCommentEntity = optionalForumParentCommentEntity.get();
                if (forumParentCommentEntity.getParentComment() != -1) {
                    addConstraintViolation(context, "bad parent");
                    return false;
                }
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
