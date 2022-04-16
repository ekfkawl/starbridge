package kr.starbridge.web.domain.bridge.service.impl;

import kr.starbridge.web.domain.bridge.entity.PlayerEntity;
import kr.starbridge.web.domain.bridge.repository.PlayerRepository;
import kr.starbridge.web.domain.bridge.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlayerImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Transactional
    @Override
    public Optional<PlayerEntity> getPlayerFlag(String id) {
        return playerRepository.findById(id);
    }

    @Transactional
    @Override
    public PlayerEntity save(PlayerEntity playerEntity) {
        return playerRepository.save(playerEntity);
    }
}
