package kr.starbridge.web.domain.bridge.service.impl;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import kr.starbridge.web.domain.bridge.entity.BattleTagId;
import kr.starbridge.web.domain.bridge.repository.BattleTagRepository;
import kr.starbridge.web.domain.bridge.service.BattleTagService;
import kr.starbridge.web.global.common.enums.ExceptionEnum;
import kr.starbridge.web.global.common.response.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static kr.starbridge.web.domain.bridge.enums.FunctionURIEnum.URI_BLACKLIST_TAG;
import static kr.starbridge.web.domain.bridge.enums.FunctionURIEnum.Url;

@Service
@RequiredArgsConstructor
public class BattleTagServiceImpl implements BattleTagService {

    private final BattleTagRepository battleTagRepository;

    @Transactional
    @Override
    public List<BattleTagEntity> getBattleTagsEx(String id) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return getBattleTags(id, Sort.by(Sort.Direction.DESC, "modifyDt"));
    }

    @Transactional
    @Override
    public List<BattleTagEntity> getBattleTags(String id) {
        return battleTagRepository.findByIdMemberId(id);
    }

    @Transactional
    @Override
    public List<BattleTagEntity> getBattleTags(String id, Sort sort) {
        return battleTagRepository.findByIdMemberId(id, sort);
    }

    @Transactional
    @Override
    public BattleTagEntity getBattleTag(String id, String tag) {
        return battleTagRepository.findByIdMemberIdAndIdTag(id, tag);
    }

    @Transactional
    @Override
    public BattleTagEntity upsertBattleTag(BattleTagEntity battleTagEntity) {

        /** 기존에 등록된 배틀태그 인지 */
        boolean isExistsTag = isExistsTag(battleTagEntity.getId().getMemberId(), battleTagEntity.getId().getTag());
        if (isExistsTag && !battleTagEntity.getId().getTag().equals(battleTagEntity.getPrevTag())) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }

        if (!StringUtils.isNullOrEmpty(battleTagEntity.getPrevTag())) {
            /** 기존에 존재하면 수정 */
            update(battleTagEntity);
        }else {
            /** 새로 등록 */
            save(battleTagEntity);
        }

        return battleTagEntity;
    }

    @Transactional
    @Override
    public BattleTagEntity updateBattleTagExport(BattleTagEntity battleTagEntity) {
        Optional<BattleTagEntity> optionalBattleTagEntity = Optional.ofNullable(getBattleTag(battleTagEntity.getId().getMemberId(), battleTagEntity.getId().getTag()));
        if (!optionalBattleTagEntity.isPresent()) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }
        BattleTagEntity saveBattleTagEntity = optionalBattleTagEntity.get();
        saveBattleTagEntity.setExport(battleTagEntity.isExport());

        return save(saveBattleTagEntity);
    }

    @Transactional
    @Override
    public boolean isExistsTag(String id, String tag) {
        return battleTagRepository.existsByIdMemberIdAndIdTag(id, tag);
    }

    @Transactional
    @Override
    public List<BattleTagEntity> getRemoteExportTags(List<BattleTagEntity> battleTagEntities, String pullId) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        List<BattleTagEntity> remoteBattleTagEntities = getBattleTagsEx(pullId).stream()
                .filter(f -> f.isExport())
                .collect(Collectors.toList());

        /** 로컬과 중복안되는 배틀태그 */
        return remoteBattleTagEntities.stream()
                .filter(local -> battleTagEntities.stream()
                        .noneMatch(f -> f.getId().getTag().equals(local.getId().getTag())))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<BattleTagEntity> importTags(String id, List<BattleTagEntity> battleTagEntities) {
        List<BattleTagEntity> impBattleTagEntities = new ArrayList<>();

        if (battleTagEntities.size() <= 0) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION.setMessage(String.format("*href=%s", Url(URI_BLACKLIST_TAG))));
        }

        /** import 대상 시퀀스와 일치하는 배틀태그 */
        getBattleTags(battleTagEntities.get(0).getId().getMemberId()).stream()
                .filter(imp -> battleTagEntities.stream()
                        .anyMatch(f -> Objects.equals(f.getSeq(), imp.getSeq())))
                .forEach(p -> {
                    BattleTagEntity battleTagEntity = BattleTagEntity.builder()
                            .id(new BattleTagId(id, p.getId().getTag()))
                            .memo(p.getMemo())
                            .isExport(true)
                            .build();
                    impBattleTagEntities.add(battleTagEntity);
                });

        return saveAll(impBattleTagEntities);
    }

    @Transactional
    @Override
    public BattleTagEntity save(BattleTagEntity battleTagEntity) {
        return battleTagRepository.save(battleTagEntity);
    }

    @Transactional
    @Override
    public List<BattleTagEntity> saveAll(List<BattleTagEntity> battleTagEntities) {
        return battleTagRepository.saveAll(battleTagEntities);
    }

    @Transactional
    @Override
    public int update(BattleTagEntity battleTagEntity) {
        return battleTagRepository.update(battleTagEntity);
    }

    @Transactional
    @Override
    public void delete(BattleTagId battleTagId) {
        battleTagRepository.deleteById(battleTagId);
    }
}
