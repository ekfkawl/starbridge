package kr.starbridge.web.domain.bridge.service.impl;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.bridge.dto.BattleTagDTO;
import kr.starbridge.web.domain.bridge.entity.BattleTagEntity;
import kr.starbridge.web.domain.bridge.entity.BattleTagId;
import kr.starbridge.web.domain.bridge.repository.BattleTagRepository;
import kr.starbridge.web.domain.bridge.service.BattleTagService;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.utils.BeanSubUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static kr.starbridge.web.global.utils.EscapeUtils.escape;

@Service
@RequiredArgsConstructor
public class BattleTagServiceImpl implements BattleTagService {

    private final BattleTagRepository battleTagRepository;

    @Transactional
    @Override
    public ApiResult<List<BattleTagDTO>> getBattleTagsEx(String id) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

        List<BattleTagEntity> battleTagEntities = getBattleTags(id);
        List<BattleTagDTO> battleTagDTOList = BeanSubUtils.copyPropertiesForList(battleTagEntities, new ArrayList<>(), BattleTagDTO.class);

        return new ApiResult<>(battleTagDTOList);
    }

    @Transactional
    @Override
    public List<BattleTagEntity> getBattleTags(String id) {
        return battleTagRepository.findByIdMemberId(id);
    }

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
                .prevTag(escape(battleTagDTO.getPrevTag()))
                .build();

        if (!StringUtils.isNullOrEmpty(battleTagDTO.getPrevTag())) {
            /** 기존에 존재하면 수정 */
            update(battleTagEntity);
        }else {
            /** 새로 등록 */
            save(battleTagEntity);
        }

        return new ApiResult<>();
    }

    @Transactional
    @Override
    public boolean isExistsTag(String id, String tag) {
        return battleTagRepository.existsByIdMemberIdAndIdTag(id, tag);
    }

    @Transactional
    @Override
    public BattleTagEntity save(BattleTagEntity battleTagEntity) {
        return battleTagRepository.save(battleTagEntity);
    }

    @Transactional
    @Override
    public int update(BattleTagEntity battleTagEntity) {
        return battleTagRepository.update(battleTagEntity);
    }

    @Override
    public void delete(BattleTagId battleTagId) {
        battleTagRepository.deleteById(battleTagId);
    }
}
