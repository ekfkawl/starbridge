package kr.starbridge.web.domain.forum.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
     * 아이디
     */
    @JsonIgnore
    private String memberId;
    /**
     * 닉네임
     */
    private String memberName;
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

    public int getCategory() {
        switch (category) {
            case 1:
                categoryName = "Q/A";
                categoryColor = 0x0047FF;
                break;
            case 2:
                categoryName = "건의사항";
                categoryColor = 0xFFE400;
                break;
            case 3:
                categoryName = "정보";
                categoryColor = 0x00D36E;
                break;
            case -1:
                categoryName = "공지사항";
                categoryColor = 0xED207B;
                break;
            default:
                categoryName = "잡담";
                categoryColor = 0xAFAFAF;
        }
        return category;
    }

    public LocalDateTime getCreateDt() {
        betweenTime = DateUtils.getBeforeDiffToString(createDt);
        return createDt;
    }
}
