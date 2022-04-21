package kr.starbridge.web.domain.forum.service.impl;

import kr.starbridge.web.domain.forum.entity.ForumCommentEntity;
import kr.starbridge.web.domain.forum.repository.ForumCommentRepository;
import kr.starbridge.web.domain.forum.service.ForumCommentService;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.global.utils.BeanSubUtils;
import kr.starbridge.web.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForumCommentServiceImpl implements ForumCommentService {

    private final ForumCommentRepository forumCommentRepository;

    @Transactional
    @Override
    public List<ForumCommentEntity> getComments(long contentSeq) {
        return forumCommentRepository.findByContentSeq(contentSeq, Sort.by(Sort.Direction.DESC, "createDt"));
    }

    @Transactional
    @Override
    public Optional<ForumCommentEntity> getComment(long seq) {
        return forumCommentRepository.findById(seq);
    }

    @Transactional
    @Override
    public HashMap<String, Object> getCommentWriter(long seq) {
        HashMap<String, Object> hm = new HashMap<>();

        Optional<ForumCommentEntity> optionalForumCommentEntity = getComment(seq);
        if (!optionalForumCommentEntity.isPresent()) {
            /** case: insert */
            hm.put("isLocal", true);
            return hm;
        }

        /** case: update */
        ForumCommentEntity forumCommentEntity = optionalForumCommentEntity.get();

        MemberDTO selfMemberDTO = SecurityUtils.getSelfInfo();

        hm.put("isLocal", selfMemberDTO.getId().equals(forumCommentEntity.getMember().getId()));
        hm.put("entity", forumCommentEntity);

        return hm;
    }

    @Transactional
    @Override
    public ForumCommentEntity save(ForumCommentEntity forumCommentEntity) {
        if (forumCommentEntity.getSeq() != null) {
            Optional<ForumCommentEntity> optionalForumCommentEntity = forumCommentRepository.findById(forumCommentEntity.getSeq());
            if (optionalForumCommentEntity.isPresent()) {
                ForumCommentEntity currentForumCommentEntity = optionalForumCommentEntity.get();
                BeanUtils.copyProperties(forumCommentEntity, currentForumCommentEntity, BeanSubUtils.getNullPropertyNames(forumCommentEntity));

                return currentForumCommentEntity;
            }
        }
        return forumCommentRepository.save(forumCommentEntity);
    }

    @Transactional
    @Override
    public void delete(ForumCommentEntity forumCommentEntity) {
        List<ForumCommentEntity> forumCommentEntityList = forumCommentRepository.findByParentComment(forumCommentEntity.getSeq());
        /** 연관 관계가 존재하는 경우 */
        if (forumCommentEntityList.size() > 0) {
            forumCommentEntity.setComment("[삭제된 댓글입니다]");
            save(forumCommentEntity);
        }else {
            forumCommentRepository.deleteBySeq(forumCommentEntity.getSeq());
        }
    }
}
