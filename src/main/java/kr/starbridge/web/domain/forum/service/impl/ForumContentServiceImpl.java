package kr.starbridge.web.domain.forum.service.impl;

import kr.starbridge.web.domain.forum.entity.ForumContentEntity;
import kr.starbridge.web.domain.forum.repository.ForumContentRepository;
import kr.starbridge.web.domain.forum.service.ForumContentService;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.global.utils.BeanSubUtils;
import kr.starbridge.web.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForumContentServiceImpl implements ForumContentService {

    private final ForumContentRepository forumContentRepository;

    @Transactional
    @Override
    public List<ForumContentEntity> getContents(int page, String search) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by("isFix").descending().and(Sort.by("createDt").descending()));

        List<ForumContentEntity> forumContentEntities = page == 0 ? getFixContents() : new ArrayList<>();

        forumContentEntities.addAll(forumContentRepository.findAllByTitleContainingOrContentContaining(search, search, pageable).stream()
                .filter(p -> !p.isFix())
                .collect(Collectors.toList()));

        return forumContentEntities;
    }

    @Transactional
    @Override
    public Optional<ForumContentEntity> getContent(long seq) {
        return forumContentRepository.findById(seq);
    }

    @Transactional
    @Override
    public HashMap<String, Object> getContentWriter(long seq) {
        HashMap<String, Object> hm = new HashMap<>();

        Optional<ForumContentEntity> optionalForumContentEntity = getContent(seq);
        if (!optionalForumContentEntity.isPresent()) {
            /** case: insert */
            hm.put("isLocal", true);
            return hm;
        }

        /** case: update */
        ForumContentEntity forumContentEntity = optionalForumContentEntity.get();
        MemberDTO selfMemberDTO = SecurityUtils.getSelfInfo();

        hm.put("isLocal", selfMemberDTO.getId().equals(forumContentEntity.getMember().getId()));
        hm.put("entity", forumContentEntity);

        return hm;
    }

    @Transactional
    @Override
    public int incViewCount(Long seq) {
        return forumContentRepository.incViewCount(seq);
    }

    @Transactional
    @Override
    public List<ForumContentEntity> getFixContents() {
        return forumContentRepository.findByIsFixOrderByCreateDtDesc(true);
    }

    @Transactional
    @Override
    public ForumContentEntity save(ForumContentEntity forumContentEntity) {
        if (forumContentEntity.getSeq() != null) {
            Optional<ForumContentEntity> optionalForumContentEntity = forumContentRepository.findById(forumContentEntity.getSeq());
            if (optionalForumContentEntity.isPresent()) {
                ForumContentEntity currentForumContentEntity = optionalForumContentEntity.get();
                BeanUtils.copyProperties(forumContentEntity, currentForumContentEntity, BeanSubUtils.getNullPropertyNames(forumContentEntity));

                return currentForumContentEntity;
            }
        }
        return forumContentRepository.save(forumContentEntity);
    }

    @Transactional
    @Override
    public void delete(ForumContentEntity forumContentEntity) {
        Optional<ForumContentEntity> optionalForumContentEntity = forumContentRepository.findById(forumContentEntity.getSeq());
        forumContentRepository.delete(optionalForumContentEntity.get());
    }
}
