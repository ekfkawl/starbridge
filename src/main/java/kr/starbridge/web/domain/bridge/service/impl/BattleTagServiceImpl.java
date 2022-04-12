package kr.starbridge.web.domain.bridge.service.impl;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.bridge.dto.BattleTagDTO;
import kr.starbridge.web.domain.bridge.dto.BattleTagImportDTO;
import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import kr.starbridge.web.domain.bridge.entity.BattleTagId;
import kr.starbridge.web.domain.bridge.repository.BattleTagRepository;
import kr.starbridge.web.domain.bridge.service.BattleTagService;
import kr.starbridge.web.global.common.enums.ExceptionEnum;
import kr.starbridge.web.global.common.response.ApiException;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.utils.BeanSubUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static kr.starbridge.web.global.utils.EscapeUtils.escape;

@Service
@RequiredArgsConstructor
public class BattleTagServiceImpl implements BattleTagService {

    private final BattleTagRepository battleTagRepository;

    @Transactional
    @Override
    public List<BattleTagDTO> getBattleTagsEx(String id) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        List<BattleTagEntity> battleTagEntities = getBattleTags(id, Sort.by(Sort.Direction.DESC, "modifyDt"));
        List<BattleTagDTO> battleTagDTOList = BeanSubUtils.copyPropertiesForList(battleTagEntities, new ArrayList<>(), BattleTagDTO.class);

        return battleTagDTOList;
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
    public ApiResult<Object> upsertBattleTag(BattleTagDTO battleTagDTO) {

        /** 기존에 등록된 배틀태그 인지 */
        boolean isExistsTag = isExistsTag(battleTagDTO.getId().getMemberId(), battleTagDTO.getId().getTag());
        if (isExistsTag && !battleTagDTO.getId().getTag().equals(battleTagDTO.getPrevTag())) {
            return new ApiResult<>("이미 등록된 배틀태그 입니다");
        }

        BattleTagEntity battleTagEntity = BattleTagEntity.builder()
                .id(new BattleTagId(battleTagDTO.getId().getMemberId(), battleTagDTO.getId().getTag()))
                .memo(escape(battleTagDTO.getMemo()))
                .isExport(true)
                .prevTag(escape(battleTagDTO.getPrevTag()))
                .build();

        if (!StringUtils.isNullOrEmpty(battleTagDTO.getPrevTag())) {
            /** 기존에 존재하면 수정 */
            update(battleTagEntity);
        }else {
            /** 새로 등록 */
            save(battleTagEntity);
        }

        return new ApiResult<>(battleTagDTO);
    }

    @Transactional
    @Override
    public ApiResult<Object> updateBattleTagExport(BattleTagDTO battleTagDTO) {
        BattleTagEntity battleTagEntity = getBattleTag(battleTagDTO.getId().getMemberId(), battleTagDTO.getId().getTag());
        battleTagEntity.setExport(battleTagDTO.isExport());
        save(battleTagEntity);

        return new ApiResult<>(battleTagDTO);
    }

    @Transactional
    @Override
    public boolean isExistsTag(String id, String tag) {
        return battleTagRepository.existsByIdMemberIdAndIdTag(id, tag);
    }

    @Transactional
    @Override
    public List<BattleTagDTO> getRemoteExportTags(List<BattleTagDTO> localBattleTagDTOList, String id) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

        List<BattleTagDTO> remoteBattleTagDTOList = getBattleTagsEx(id).stream()
                .filter(f -> f.isExport())
                .collect(Collectors.toList());

        /** 로컬과 중복안되는 배틀태그 */
        return remoteBattleTagDTOList.stream()
                .filter(local -> localBattleTagDTOList.stream()
                        .noneMatch(f -> f.getId().getTag().equals(local.getId().getTag())))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<BattleTagEntity> importTags(String id, BattleTagImportDTO battleTagImportDTO) {
        /** 파라미터 NullOrEmpty 체크 */
        if (StringUtils.isNullOrEmpty(battleTagImportDTO.getId())) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }
        List<Long> seqList = battleTagImportDTO.getSeq();
        if (seqList.size() <= 0) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }

        List<BattleTagEntity> impBattleTagEntities = new ArrayList<>();

        /** import 대상 시퀀스와 일치하는 배틀태그 */
        getBattleTags(battleTagImportDTO.getId()).stream()
                .filter(imp -> seqList.stream()
                        .anyMatch(f -> f.equals(imp.getSeq())))
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
