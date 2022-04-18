package kr.starbridge.web.domain.forum.service;

import kr.starbridge.web.domain.forum.entity.ForumContentEntity;

import java.util.List;

public interface ForumContentService {
    /**
     * 게시물 리스트
     * @param page
     * @param search
     * @return
     */
    List<ForumContentEntity> getContents(int page, String search);
}
