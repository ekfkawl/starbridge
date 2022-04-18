package kr.starbridge.web.domain.forum.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.domain.member.entity.MemberEntity;
import kr.starbridge.web.global.utils.DateUtils;
import lombok.*;

import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForumContentDTO {
    /**
     * seq
     */
    @Id
    private long seq;
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
    private String title;
    /**
     * 내용
     */
    private String content;
    /**
     * 조회수
     */
    private int viewCount;
    /**
     * 상단 고정
     */
    private boolean isFix;
    /**
     * 생성일
     */
    private LocalDateTime createDt;
    /**
     * 생성일 ~ 현재 차이
     */
    private String betweenTime;

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
        return DateUtils.getBeforeDiffToString(createDt);
    }
}
