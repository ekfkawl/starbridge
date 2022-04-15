package kr.starbridge.web.domain.bridge.service;

import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface BattleTagService {
    /**
     * 블랙리스트 목록
     * @param id
     * @return
     */
    List<BattleTagEntity> getBattleTagsEx(String id);

    /**
     * 블랙리스트 목록
     * @param id
     * @return
     */
    List<BattleTagEntity> getBattleTags(String id);

    /**
     * 블랙리스트 목록 (정렬)
     * @param id
     * @param sort
     * @return
     */
    List<BattleTagEntity> getBattleTags(String id, Sort sort);

    /**
     * 블랙리스트
     * @param id
     * @param tag
     * @return
     */
    BattleTagEntity getBattleTag(String id, String tag);

    /**
     * 블랙리스트 등록/수정
     * @param battleTagEntity
     * @return
     */
    BattleTagEntity upsertBattleTag(BattleTagEntity battleTagEntity);

    /**
     * 블랙리스트 추출 대상 수정
     * @param battleTagEntity
     * @return
     */
    BattleTagEntity updateBattleTagExport(BattleTagEntity battleTagEntity);

    /**
     * 배틀태그 중복 체크
     * @param id
     * @param tag
     * @return
     */
    boolean isExistsTag(String id, String tag);

    /**
     * 추출 허용된 블랙리스트 목록
     * @param battleTagEntities
     * @param pullId
     * @return
     */
    List<BattleTagEntity> getRemoteExportTags(List<BattleTagEntity> battleTagEntities, String pullId);

    /**
     * 블랙리스트 임포트
     * @param id
     * @param battleTagEntities
     * @return
     */
    List<BattleTagEntity> importTags(String id, List<BattleTagEntity> battleTagEntities);

    /**
     * save
     * @param battleTagEntity
     * @return
     */
    BattleTagEntity save(BattleTagEntity battleTagEntity);

    /**
     * saveAll
     * @param battleTagEntities
     * @return
     */
    List<BattleTagEntity> saveAll(List<BattleTagEntity> battleTagEntities);

    /**
     * update
     * @param battleTagEntity
     * @return
     */
    int update(BattleTagEntity battleTagEntity);

    /**
     * delete
     * @param battleTagEntity
     */
    void delete(BattleTagEntity battleTagEntity);
}
