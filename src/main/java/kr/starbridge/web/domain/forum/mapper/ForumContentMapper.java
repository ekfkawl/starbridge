package kr.starbridge.web.domain.forum.mapper;

import kr.starbridge.web.domain.forum.dto.ForumContentDTO;
import kr.starbridge.web.domain.forum.entity.ForumContentEntity;
import kr.starbridge.web.domain.member.mapper.MemberMapper;

import java.util.ArrayList;
import java.util.List;

public class ForumContentMapper {

    /**
     * ForumContentDTO -> Entity
     * @param forumContentDTO
     * @return
     */
    public static ForumContentEntity toForumContentEntity(ForumContentDTO forumContentDTO) {
        return ForumContentEntity.builder()
                .seq(forumContentDTO.getSeq())
                .member(MemberMapper.toMemberEntity(forumContentDTO.getMember()))
                .category(forumContentDTO.getCategory())
                .title(forumContentDTO.getTitle())
                .content(forumContentDTO.getContent())
                .viewCount(forumContentDTO.getViewCount())
                .isFix(forumContentDTO.isFix())
                .createDt(forumContentDTO.getCreateDt())
                .build();
    }

    /**
     * ForumContentDTO -> Entity
     * @param forumContentDTOList
     * @return
     */
    public static List<ForumContentEntity> toForumContentEntity(List<ForumContentDTO> forumContentDTOList) {
        List<ForumContentEntity> res = new ArrayList<>();
        for (ForumContentDTO forumContentDTO : forumContentDTOList) {
            res.add(toForumContentEntity(forumContentDTO));
        }
        return res;
    }
    
    /**
     * Entity -> ForumContentDTO
     * @param forumContentEntity
     * @return
     */
    public static ForumContentDTO toForumContentDTO(ForumContentEntity forumContentEntity) {
        return ForumContentDTO.builder()
                .seq(forumContentEntity.getSeq())
                .member(MemberMapper.toMemberDTO(forumContentEntity.getMember()))
                .category(forumContentEntity.getCategory())
                .title(forumContentEntity.getTitle())
                .content(forumContentEntity.getContent())
                .viewCount(forumContentEntity.getViewCount())
                .isFix(forumContentEntity.isFix())
                .createDt(forumContentEntity.getCreateDt())
                .build();
    }

    /**
     * Entity -> ForumContentDTO
     * @param forumContentEntities
     * @return
     */
    public static List<ForumContentDTO> toForumContentDTO(List<ForumContentEntity> forumContentEntities) {
        List<ForumContentDTO> res = new ArrayList<>();
        for (ForumContentEntity forumContentEntity : forumContentEntities) {
            res.add(toForumContentDTO(forumContentEntity));
        }
        return res;
    }
}
