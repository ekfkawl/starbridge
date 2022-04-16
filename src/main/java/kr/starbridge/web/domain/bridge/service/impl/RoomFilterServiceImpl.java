package kr.starbridge.web.domain.bridge.service.impl;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.bridge.entity.RoomFilterEntity;
import kr.starbridge.web.domain.bridge.entity.RoomFilterId;
import kr.starbridge.web.domain.bridge.repository.RoomFilterRepository;
import kr.starbridge.web.domain.bridge.service.RoomFilterService;
import kr.starbridge.web.global.common.enums.ExceptionEnum;
import kr.starbridge.web.global.common.response.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static kr.starbridge.web.domain.bridge.enums.FunctionURIEnum.URI_ROOM_FILTER;
import static kr.starbridge.web.domain.bridge.enums.FunctionURIEnum.Url;

@Service
@RequiredArgsConstructor
public class RoomFilterServiceImpl implements RoomFilterService {

    private final RoomFilterRepository roomFilterRepository;

    @Transactional
    @Override
    public List<RoomFilterEntity> getKeywordsEx(String id) {
        return getKeywords(id, Sort.by(Sort.Direction.DESC, "modifyDt"));
    }

    @Transactional
    @Override
    public List<RoomFilterEntity> getKeywords(String id) {
        return roomFilterRepository.findByIdMemberId(id);
    }

    @Transactional
    @Override
    public List<RoomFilterEntity> getKeywords(String id, Sort sort) {
        return roomFilterRepository.findByIdMemberId(id, sort);
    }

    @Transactional
    @Override
    public RoomFilterEntity getKeyword(String id, String hash) {
        return roomFilterRepository.findByIdMemberIdAndIdKeyword(id, hash);
    }

    @Transactional
    @Override
    public RoomFilterEntity upsertKeyword(RoomFilterEntity roomFilterEntity) {
        if (!StringUtils.isNullOrEmpty(roomFilterEntity.getPrevKeyword())) {
            /** 기존에 존재하면 수정 */
            update(roomFilterEntity);
        }else {
            /** 새로 등록 */
            save(roomFilterEntity);
        }

        return roomFilterEntity;
    }

    @Transactional
    @Override
    public RoomFilterEntity updateKeywordExport(RoomFilterEntity roomFilterEntity) {
        Optional<RoomFilterEntity> optionalRoomFilterEntity = Optional.ofNullable(getKeyword(roomFilterEntity.getId().getMemberId(), roomFilterEntity.getId().getKeyword()));
        if (!optionalRoomFilterEntity.isPresent()) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }
        RoomFilterEntity saveRoomFilterEntity = optionalRoomFilterEntity.get();
        saveRoomFilterEntity.setExport(roomFilterEntity.isExport());

        return save(saveRoomFilterEntity);
    }

    @Transactional
    @Override
    public boolean isExistsKeyword(String id, String hash) {
        return roomFilterRepository.existsByIdMemberIdAndIdKeyword(id, hash);
    }

    @Transactional
    @Override
    public List<RoomFilterEntity> getRemoteExportKeywords(List<RoomFilterEntity> roomFilterEntities, String pullId) {
        List<RoomFilterEntity> remoteRoomFilterEntities = getKeywordsEx(pullId).stream()
                .filter(f -> f.isExport())
                .collect(Collectors.toList());

        /** 로컬과 중복안되는 키워드 */
        return remoteRoomFilterEntities.stream()
                .filter(local -> roomFilterEntities.stream()
                        .noneMatch(f -> f.getId().getKeyword().equals(local.getId().getKeyword())))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<RoomFilterEntity> importKeywords(String id, List<RoomFilterEntity> roomFilterEntities) {
        List<RoomFilterEntity> impRoomFilterEntities = new ArrayList<>();

        if (roomFilterEntities.size() <= 0) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION.setMessage(String.format("*href=%s", Url(URI_ROOM_FILTER))));
        }

        /** import 대상 시퀀스와 일치하는 키워드 */
        getKeywords(roomFilterEntities.get(0).getId().getMemberId()).stream()
                .filter(imp -> roomFilterEntities.stream()
                        .anyMatch(f -> Objects.equals(f.getSeq(), imp.getSeq())))
                .forEach(p -> {
                    RoomFilterEntity battleTagEntity = RoomFilterEntity.builder()
                            .id(new RoomFilterId(id, p.getId().getKeyword()))
                            .isExport(true)
                            .build();
                    impRoomFilterEntities.add(battleTagEntity);
                });

        return saveAll(impRoomFilterEntities);
    }

    @Transactional
    @Override
    public RoomFilterEntity save(RoomFilterEntity roomFilterEntity)  {
        return roomFilterRepository.save(roomFilterEntity);
    }

    @Transactional
    @Override
    public List<RoomFilterEntity> saveAll(List<RoomFilterEntity> roomFilterEntities) {
        return roomFilterRepository.saveAll(roomFilterEntities);
    }

    @Transactional
    @Override
    public int update(RoomFilterEntity roomFilterEntity) {
        return roomFilterRepository.update(roomFilterEntity);
    }

    @Transactional
    @Override
    public void delete(RoomFilterEntity roomFilterEntity) {
        roomFilterRepository.delete(roomFilterEntity);
    }
}
