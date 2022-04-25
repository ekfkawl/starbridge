package kr.starbridge.web.domain.forum.service.impl;

import kr.starbridge.web.domain.forum.entity.ForumBanEntity;
import kr.starbridge.web.domain.forum.repository.ForumBanRepository;
import kr.starbridge.web.domain.forum.service.ForumBanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumBanServiceImpl implements ForumBanService {
    private final ForumBanRepository forumBanRepository;

    @Transactional
    @Override
    public List<ForumBanEntity> getBans() {
        return forumBanRepository.findAll();
    }

    @Transactional
    @Override
    public void addBan(ForumBanEntity forumBanEntity) {
        forumBanRepository.save(forumBanEntity);
    }
}
