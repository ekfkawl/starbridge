package kr.starbridge.web.domain.forum.controller;

import kr.starbridge.web.domain.forum.dto.ForumContentDTO;
import kr.starbridge.web.domain.forum.entity.ForumContentEntity;
import kr.starbridge.web.domain.forum.service.ForumContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static kr.starbridge.web.domain.forum.mapper.ForumContentMapper.toForumContentDTO;

/**
 * 포럼 게시물 리스트 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class ForumController {

    private final ForumContentService forumContentService;

    @GetMapping("/forum")
    public ModelAndView forum(@RequestParam(required = false, defaultValue = "") String search) {

        ModelAndView mv = new ModelAndView("forum");

        List<ForumContentEntity> forumContentEntities = forumContentService.getContents(0, search);

        List<ForumContentDTO> forumContentDTOS = toForumContentDTO(forumContentEntities);
        System.out.println("forumContentDTOS = " + forumContentDTOS);

        mv.addObject("forumContentDTOList", toForumContentDTO(forumContentEntities));

        return mv;
    }
}
