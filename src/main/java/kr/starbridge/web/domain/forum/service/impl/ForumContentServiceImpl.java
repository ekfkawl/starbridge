package kr.starbridge.web.domain.forum.service.impl;

import kr.starbridge.web.domain.forum.entity.ForumContentEntity;
import kr.starbridge.web.domain.forum.repository.ForumContentRepository;
import kr.starbridge.web.domain.forum.service.ForumContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ForumContentServiceImpl implements ForumContentService {

    private final ForumContentRepository forumContentRepository;

    @Override
    public List<ForumContentEntity> getContents(int page, String search) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "createDt"));
        return forumContentRepository.findAllByTitleContainingOrContentContaining(search, search, pageable);
    }
}
