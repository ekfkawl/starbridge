package kr.starbridge.web.domain.bridge.service.impl;

import kr.starbridge.web.domain.bridge.entity.RoomRoleEntity;
import kr.starbridge.web.domain.bridge.repository.RoomRoleRepository;
import kr.starbridge.web.domain.bridge.service.RoomRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomRoleImpl implements RoomRoleService {

    private final RoomRoleRepository roomRoleRepository;

    @Transactional
    @Override
    public Optional<RoomRoleEntity> getRole(String id) {
        return roomRoleRepository.findById(id);
    }

    @Transactional
    @Override
    public RoomRoleEntity save(RoomRoleEntity roomRoleEntity) {
        return roomRoleRepository.save(roomRoleEntity);
    }
}
