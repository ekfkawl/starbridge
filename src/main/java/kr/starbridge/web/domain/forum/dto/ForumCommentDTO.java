package kr.starbridge.web.domain.forum.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.forum.validator.annotation.ForumRequest;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.global.Regex;
import kr.starbridge.web.global.common.response.ValidationGroups;
import kr.starbridge.web.global.utils.DateUtils;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import static kr.starbridge.web.global.utils.EscapeUtils.escape;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ForumRequest(clazz = ForumCommentDTO.class)
public class ForumCommentDTO {
    /**
     * seq
     */
    private Long seq;
    /**
     * content_seq
     */
    @JsonProperty("content_seq")
    private Long contentSeq;
    /**
     * Member
     */
    private MemberDTO member;
    /**
     * 내용
     */
    @NotEmpty(message = "내용을 입력해주세요", groups = ValidationGroups.NotEmptyGroup.class)
    private String comment;
    /**
     * 상위 댓글 seq
     */
    @JsonProperty("parent_comment")
    private long parentComment;
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
     * 수정일
     */
    @JsonIgnore
    private LocalDateTime modifyDt;
    /**
     * 생성일 ~ 현재 차이
     */
    @JsonIgnore
    private String betweenTime;

    public String getComment() {
        return !StringUtils.isNullOrEmpty(comment) ? escape(comment.replaceAll(Regex.TRIM, "")) : null;
    }

    public String getBetweenTime() {
        return modifyDt == null ? null : DateUtils.getBeforeDiffToString(modifyDt);
    }

}
