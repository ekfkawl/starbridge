package kr.starbridge.web.domain.bridge.service;

import kr.starbridge.web.domain.bridge.entity.RoomFilterEntity;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface RoomFilterService {
    /**
     * 필터링 키워드 목록
     * @param id
     * @return
     */
    List<RoomFilterEntity> getKeywordsEx(String id);

    /**
     * 필터링 키워드 목록
     * @param id
     * @return
     */
    List<RoomFilterEntity> getKeywords(String id);

    /**
     * 필터링 키워드 목록 (정렬)
     * @param id
     * @param sort
     * @return
     */
    List<RoomFilterEntity> getKeywords(String id, Sort sort);

    /**
     * 필터링 키워드
     * @param id
     * @param keyword
     * @return
     */
    RoomFilterEntity getKeyword(String id, String keyword);


    /**
     * 필터링 키워드 등록/수정
     * @param roomFilterEntity
     * @return
     */
    RoomFilterEntity upsertKeyword(RoomFilterEntity roomFilterEntity);

    /**
     * 필터링 키워드 추출 대상 수정
     * @param roomFilterEntity
     * @return
     */
    RoomFilterEntity updateKeywordExport(RoomFilterEntity roomFilterEntity);

    /**
     * 필터링 키워드 중복 체크
     * @param id
     * @param keyword
     * @return
     */
    boolean isExistsKeyword(String id, String keyword);

    /**
     * 추출 허용된 필터링 키워드 목록
     * @param roomFilterEntities
     * @param pullId
     * @return
     */
    List<RoomFilterEntity> getRemoteExportKeywords(List<RoomFilterEntity> roomFilterEntities, String pullId);

    /**
     * 필터링 키워드 임포트
     * @param id
     * @param roomFilterEntities
     * @return
     */
    List<RoomFilterEntity> importKeywords(String id, List<RoomFilterEntity> roomFilterEntities);
    
    /**
     * save
     * @param roomFilterEntity
     * @return
     */
    RoomFilterEntity save(RoomFilterEntity roomFilterEntity);

    /**
     * saveAll
     * @param roomFilterEntities
     * @return
     */
    List<RoomFilterEntity> saveAll(List<RoomFilterEntity> roomFilterEntities);

    /**
     * update
     * @param roomFilterEntity
     * @return
     */
    int update(RoomFilterEntity roomFilterEntity);

    /**
     * delete
     * @param roomFilterEntity
     */
    void delete(RoomFilterEntity roomFilterEntity);
}
