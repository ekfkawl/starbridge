package kr.starbridge.web.domain.upload.controller;

import kr.starbridge.web.domain.upload.dto.UploadedDTO;
import kr.starbridge.web.domain.upload.service.FileUploadService;
import kr.starbridge.web.global.common.enums.ExceptionEnum;
import kr.starbridge.web.global.common.response.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * 파일 업로드 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class FileUploadController {
    private final FileUploadService fileUploadService;

    @PostMapping("api/upload")
    public UploadedDTO uploadSingle(@RequestParam(required = false) Optional<MultipartFile> file, HttpServletRequest request) throws Exception {

        if (!file.isPresent()) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION.setMessage("Null MultipartFile"));
        }

        /** 업로드 디렉토리 */
        String uploadPath = request.getServletContext().getRealPath("/resources/static/uploaded/");

        return fileUploadService.uploadSingle(file.get(), uploadPath);
    }
}
