package kr.starbridge.web.domain.forum.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.forum.validator.annotation.ForumRequest;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.domain.member.enums.RoleEnum;
import kr.starbridge.web.global.Regex;
import kr.starbridge.web.global.common.response.ValidationGroups;
import kr.starbridge.web.global.utils.DateUtils;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import static kr.starbridge.web.global.utils.EscapeUtils.escape;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ForumRequest(clazz = ForumContentDTO.class)
public class ForumContentDTO {
    /**
     * seq
     */
    private Long seq;
    /**
     * Member
     */
    private MemberDTO member;
    /**
     * 카테고리
     */
    private int category;
    /**
     * 카테고리 이름
     */
    private String categoryName;
    /**
     * 카테고리 색상
     */
    private int categoryColor;
    /**
     * 제목
     */
    @NotEmpty(message = "제목을 입력해주세요", groups = ValidationGroups.NotEmptyGroup.class)
    private String title;
    /**
     * 내용
     */
    @NotEmpty(message = "내용을 입력해주세요", groups = ValidationGroups.NotEmptyGroup.class)
    private String content;
    /**
     * 조회수
     */
    private Integer viewCount;
    /**
     * 상단 고정
     */
    private boolean isFix;
    /**
     * ip
     */
    private String ip;
    /**
     * 생성일
     */
    @JsonIgnore
    private LocalDateTime createDt;
    /**
     * 생성일 ~ 현재 차이
     */
    private String betweenTime;
    /**
     * Comment
     */
    @JsonIgnore
    private List<ForumCommentDTO> comment;
    /**
     * 댓글수
     */
    private int commentCount;
    /**
     * 게시물 내의 멤버 프로필 이미지
     */
    private LinkedHashMap<String, String> memberImages;
    /**
     * 이미지 포함 여부
     */
    private boolean isUploadedImg;

    public int getCategory() {
        /** request 검증 */
        if (category > 3 || category < -1) {
            category = 0;
        }
        if (category == -1 && RoleEnum.ROLE_ADMIN != member.getAuth()) {
            category = 0;
        }
        return category;
    }

    public boolean isFix() {
        return (category == -1 && RoleEnum.ROLE_ADMIN == member.getAuth());
    }

    public String getTitle() {
        return !StringUtils.isNullOrEmpty(title) ? title.replaceAll(Regex.TRIM, "") : null;
    }

    public String getContent() {
        return escape(content);
    }

    public String getCategoryName() {
        switch (category) {
            case 1:
                return "Q/A";
            case 2:
                return "건의사항";
            case 3:
                return "정보";
            case -1:
                return "공지사항";
            default:
                return "잡담";
        }
    }

    public int getCategoryColor() {
        switch (category) {
            case 1:
                return 0x0047FF;
            case 2:
                return 0xFFE400;
            case 3:
                return 0x00D36E;
            case -1:
                return 0xED207B;
            default:
                return 0xAFAFAF;
        }
    }

    public String getBetweenTime() {
        return createDt == null ? null : DateUtils.getBeforeDiffToString(createDt);
    }

    public List<ForumCommentDTO> getComment() {
        return comment;
    }

    public int getCommentCount() {
        return comment == null ? 0 : comment.size();
    }

    public LinkedHashMap<String, String> getMemberImages() {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();

        lhm.put(member.getName(), member.getImg());
        if (getCommentCount() == 0) {
            return lhm;
        }

        /** 생성일 오름차순 기준으로 put */
        Collections.reverse(comment);
        for (ForumCommentDTO forumCommentDTO : comment) {
            if (lhm.size() >= 5) {
                break;
            }
            if (!lhm.containsKey(forumCommentDTO.getMember().getName())) {
                lhm.put(forumCommentDTO.getMember().getName(), forumCommentDTO.getMember().getImg());
            }
        }

        return lhm;
    }

    public boolean isUploadedImg() {
        return getContent().contains("&lt;img alt=");
    }
}
