package kr.starbridge.web.domain.forum.mapper;

import kr.starbridge.web.domain.forum.dto.ForumBanDTO;
import kr.starbridge.web.domain.forum.entity.ForumBanEntity;

public class ForumBanMapper {
    /**
     * ForumBanDTO -> Entity
     * @param forumBanDTO
     * @return
     */
    public static ForumBanEntity toForumBanEntity(ForumBanDTO forumBanDTO) {
        return ForumBanEntity.builder()
                .ip(forumBanDTO.getIp())
                .name(forumBanDTO.getName())
                .build();
    }

    /**
     * Entity -> ForumBanDTO
     * @param forumBanEntity
     * @return
     */
    public static ForumBanDTO toForumBanDTO(ForumBanEntity forumBanEntity) {
        return ForumBanDTO.builder()
                .ip(forumBanEntity.getIp())
                .name(forumBanEntity.getName())
                .build();
    }
}
