package kr.starbridge.web.domain.forum.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForumBanDTO {
    /**
     * 아이피
     */
    private String ip;
    /**
     * 닉네임
     */
    private String name;
}
