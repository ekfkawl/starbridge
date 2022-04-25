package kr.starbridge.web.domain.forum.service;

import kr.starbridge.web.domain.forum.entity.ForumBanEntity;

import java.util.List;

public interface ForumBanService {
    /**
     * 차단 된 아이피 목록
     * @return
     */
    List<ForumBanEntity> getBans();

    /**
     * 밴 등록
     * @param forumBanEntity
     */
    void addBan(ForumBanEntity forumBanEntity);
}
