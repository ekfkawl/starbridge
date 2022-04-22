package kr.starbridge.web.domain.forum.service;

import kr.starbridge.web.domain.forum.entity.ForumContentEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface ForumContentService {
    /**
     * 게시물 리스트
     * @param page
     * @param search
     * @return
     */
    List<ForumContentEntity> getContents(int page, String search);

    /**
     * 단일 게시물
     * @param seq
     * @return
     */
    Optional<ForumContentEntity> getContent(long seq);

    /**
     * 게시물 작성자 정보
     * @param seq
     * @return
     */
    HashMap<String, Object> getContentWriter(long seq);

    /**
     * 조회수 증가
     * @param seq
     * @return
     */
    int incViewCount(Long seq);

    /**
     * 고정글
     * @return
     */
    List<ForumContentEntity> getFixContents();

    /**
     * save
     * @return
     */
    ForumContentEntity save(ForumContentEntity forumContentEntity);

    /**
     * 게시물 수
     * @return
     */
    long getForumContentCount();

    /**
     * delete
     * @param forumContentEntity
     */
    void delete(ForumContentEntity forumContentEntity);
}
