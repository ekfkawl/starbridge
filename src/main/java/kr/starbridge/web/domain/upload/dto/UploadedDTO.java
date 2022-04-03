package kr.starbridge.web.domain.upload.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 업로드 결과
 */
@Getter
@Setter
@Builder
public class UploadedDTO {

    private int uploaded;
    private String fileName;
    private String refUploadPath;
}
