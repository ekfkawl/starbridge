package kr.starbridge.web.domain.bridge.service.impl;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.bridge.entity.IpEntity;
import kr.starbridge.web.domain.bridge.entity.IpId;
import kr.starbridge.web.domain.bridge.repository.IpRepository;
import kr.starbridge.web.domain.bridge.service.IpService;
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

import static kr.starbridge.web.domain.bridge.enums.FunctionURIEnum.URI_BLACKLIST_IP;
import static kr.starbridge.web.domain.bridge.enums.FunctionURIEnum.Url;

@Service
@RequiredArgsConstructor
public class IpServiceImpl implements IpService {

    private final IpRepository ipRepository;

    @Transactional
    @Override
    public List<IpEntity> getHashesEx(String id) {
        return getHashes(id, Sort.by(Sort.Direction.DESC, "modifyDt"));
    }

    @Transactional
    @Override
    public List<IpEntity> getHashes(String id) {
        return ipRepository.findByIdMemberId(id);
    }

    @Transactional
    @Override
    public List<IpEntity> getHashes(String id, Sort sort) {
        return ipRepository.findByIdMemberId(id, sort);
    }

    @Transactional
    @Override
    public IpEntity getHash(String id, String hash) {
        return ipRepository.findByIdMemberIdAndIdHash(id, hash);
    }

    @Transactional
    @Override
    public IpEntity upsertHash(IpEntity ipEntity) {
        if (!StringUtils.isNullOrEmpty(ipEntity.getPrevHash())) {
            /** 기존에 존재하면 수정 */
            update(ipEntity);
        }else {
            /** 새로 등록 */
            save(ipEntity);
        }

        return ipEntity;
    }

    @Transactional
    @Override
    public IpEntity updateHashExport(IpEntity ipEntity) {
        Optional<IpEntity> optionalIpEntity = Optional.ofNullable(getHash(ipEntity.getId().getMemberId(), ipEntity.getId().getHash()));
        if (!optionalIpEntity.isPresent()) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }
        IpEntity saveIpEntity = optionalIpEntity.get();
        saveIpEntity.setExport(ipEntity.isExport());

        return save(saveIpEntity);
    }

    @Transactional
    @Override
    public boolean isExistsHash(String id, String hash) {
        return ipRepository.existsByIdMemberIdAndIdHash(id, hash);
    }

    @Transactional
    @Override
    public List<IpEntity> getRemoteExportHashes(List<IpEntity> ipEntities, String pullId) {
        List<IpEntity> remoteIpEntities = getHashesEx(pullId).stream()
                .filter(f -> f.isExport())
                .collect(Collectors.toList());

        /** 로컬과 중복안되는 아이피 해시 */
        return remoteIpEntities.stream()
                .filter(local -> ipEntities.stream()
                        .noneMatch(f -> f.getId().getHash().equals(local.getId().getHash())))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<IpEntity> importHashes(String id, List<IpEntity> ipEntities) {
        List<IpEntity> impIpEntities = new ArrayList<>();

        if (ipEntities.size() <= 0) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION.setMessage(String.format("*href=%s", Url(URI_BLACKLIST_IP))));
        }

        /** import 대상 시퀀스와 일치하는 아이피 해시 */
        getHashes(ipEntities.get(0).getId().getMemberId()).stream()
                .filter(imp -> ipEntities.stream()
                        .anyMatch(f -> Objects.equals(f.getSeq(), imp.getSeq())))
                .forEach(p -> {
                    IpEntity battleTagEntity = IpEntity.builder()
                            .id(new IpId(id, p.getId().getHash()))
                            .memo(p.getMemo())
                            .isExport(true)
                            .build();
                    impIpEntities.add(battleTagEntity);
                });

        return saveAll(impIpEntities);
    }

    @Transactional
    @Override
    public IpEntity save(IpEntity ipEntity)  {
        return ipRepository.save(ipEntity);
    }

    @Transactional
    @Override
    public List<IpEntity> saveAll(List<IpEntity> ipEntities) {
        return ipRepository.saveAll(ipEntities);
    }

    @Transactional
    @Override
    public int update(IpEntity ipEntity) {
        return ipRepository.update(ipEntity);
    }

    @Transactional
    @Override
    public void delete(IpEntity ipEntity) {
        ipRepository.delete(ipEntity);
    }
}
