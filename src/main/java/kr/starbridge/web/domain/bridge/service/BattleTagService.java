package kr.starbridge.web.domain.bridge.service;

import kr.starbridge.web.domain.bridge.dto.BattleTagDTO;
import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import kr.starbridge.web.domain.bridge.entity.BattleTagId;
import kr.starbridge.web.global.common.response.ApiResult;
import org.springframework.data.domain.Sort;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface BattleTagService {
    /**
     * 블랙리스트 목록
     * @param id
     * @return
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    ApiResult<List<BattleTagDTO>> getBattleTagsEx(String id) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

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
     * @param battleTagDTO
     * @return
     */
    ApiResult<Object> upsertBattleTag(BattleTagDTO battleTagDTO);

    /**
     * 배틀태그 중복 체크
     * @param id
     * @param tag
     * @return
     */
    boolean isExistsTag(String id, String tag);

    /**
     * save
     * @param battleTagEntity
     * @return
     */
    BattleTagEntity save(BattleTagEntity battleTagEntity);

    /**
     * update
     * @param battleTagEntity
     * @return
     */
    int update(BattleTagEntity battleTagEntity);

    /**
     * delete
     * @param battleTagId
     */
    void delete(BattleTagId battleTagId);
}
