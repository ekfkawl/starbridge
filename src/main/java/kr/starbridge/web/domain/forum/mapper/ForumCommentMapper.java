package kr.starbridge.web.domain.forum.mapper;

import com.nhncorp.lucy.security.xss.XssPreventer;
import kr.starbridge.web.domain.forum.dto.ForumCommentDTO;
import kr.starbridge.web.domain.forum.entity.ForumCommentEntity;
import kr.starbridge.web.domain.member.mapper.MemberMapper;

import java.util.ArrayList;
import java.util.List;

public class ForumCommentMapper {

    /**
     * ForumCommentDTO -> Entity
     * @param forumCommentDTO
     * @return
     */
    public static ForumCommentEntity toForumCommentEntity(ForumCommentDTO forumCommentDTO) {
        return ForumCommentEntity.builder()
                .seq(forumCommentDTO.getSeq())
                .contentSeq(forumCommentDTO.getContentSeq())
                .member(forumCommentDTO.getMember() != null ? MemberMapper.toMemberEntity(forumCommentDTO.getMember()) : null)
                .comment(forumCommentDTO.getComment() != null ? XssPreventer.escape(forumCommentDTO.getComment()) : null)
                .parentComment(forumCommentDTO.getParentComment())
                .build();
    }

    /**
     * ForumCommentDTO -> Entity
     * @param forumCommentDTOList
     * @return
     */
    public static List<ForumCommentEntity> toForumCommentEntity(List<ForumCommentDTO> forumCommentDTOList) {
        List<ForumCommentEntity> res = new ArrayList<>();
        for (ForumCommentDTO forumCommentDTO : forumCommentDTOList) {
            res.add(toForumCommentEntity(forumCommentDTO));
        }
        return res;
    }
    
    /**
     * Entity -> ForumCommentDTO
     * @param forumCommentEntity
     * @return
     */
    public static ForumCommentDTO toForumCommentDTO(ForumCommentEntity forumCommentEntity) {
        ForumCommentDTO forumCommentDTO = ForumCommentDTO.builder()
                .seq(forumCommentEntity.getSeq())
                .contentSeq(forumCommentEntity.getContentSeq())
                .member(MemberMapper.toMemberDTO(forumCommentEntity.getMember()))
                .comment(forumCommentEntity.getComment())
                .parentComment(forumCommentEntity.getParentComment())
                .createDt(forumCommentEntity.getCreateDt())
                .modifyDt(forumCommentEntity.getModifyDt())
                .build();
        forumCommentDTO.getMember().setPw(null);
        return forumCommentDTO;
    }

    /**
     * Entity -> ForumCommentDTO
     * @param forumCommentEntities
     * @return
     */
    public static List<ForumCommentDTO> toForumCommentDTO(List<ForumCommentEntity> forumCommentEntities) {
        List<ForumCommentDTO> res = new ArrayList<>();
        for (ForumCommentEntity forumCommentEntity : forumCommentEntities) {
            res.add(toForumCommentDTO(forumCommentEntity));
        }
        return res;
    }
}
