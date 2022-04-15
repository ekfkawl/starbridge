package kr.starbridge.web.domain.bridge.service;

import kr.starbridge.web.domain.bridge.entity.IpEntity;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IpService {
    /**
     * 블랙리스트 목록
     * @param id
     * @return
     */
    List<IpEntity> getHashesEx(String id);

    /**
     * 블랙리스트 목록
     * @param id
     * @return
     */
    List<IpEntity> getHashes(String id);

    /**
     * 블랙리스트 목록 (정렬)
     * @param id
     * @param sort
     * @return
     */
    List<IpEntity> getHashes(String id, Sort sort);

    /**
     * 블랙리스트
     * @param id
     * @param hash
     * @return
     */
    IpEntity getHash(String id, String hash);


    /**
     * 블랙리스트 등록/수정
     * @param ipEntity
     * @return
     */
    IpEntity upsertHash(IpEntity ipEntity);

    /**
     * 블랙리스트 추출 대상 수정
     * @param ipEntity
     * @return
     */
    IpEntity updateHashExport(IpEntity ipEntity);

    /**
     * 아이피 해시 중복 체크
     * @param id
     * @param hash
     * @return
     */
    boolean isExistsHash(String id, String hash);

    /**
     * 추출 허용된 블랙리스트 목록
     * @param ipEntities
     * @param pullId
     * @return
     */
    List<IpEntity> getRemoteExportHashes(List<IpEntity> ipEntities, String pullId);

    /**
     * 블랙리스트 임포트
     * @param id
     * @param ipEntities
     * @return
     */
    List<IpEntity> importHashes(String id, List<IpEntity> ipEntities);
    
    /**
     * save
     * @param ipEntity
     * @return
     */
    IpEntity save(IpEntity ipEntity);

    /**
     * saveAll
     * @param ipEntities
     * @return
     */
    List<IpEntity> saveAll(List<IpEntity> ipEntities);

    /**
     * update
     * @param ipEntity
     * @return
     */
    int update(IpEntity ipEntity);

    /**
     * delete
     * @param ipEntity
     */
    void delete(IpEntity ipEntity);
}
