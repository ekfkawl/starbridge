package kr.starbridge.web.domain.upload.service;

import kr.starbridge.web.domain.upload.dto.UploadedDTO;
import kr.starbridge.web.global.Profile;
import kr.starbridge.web.global.common.enums.ExceptionEnum;
import kr.starbridge.web.global.common.response.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileUploadService {
    private final Profile profile;

    /**
     * 파일 업로드
     * @param file
     * @param uploadPath
     * @param request
     * @return
     * @throws IOException
     */
    public UploadedDTO uploadSingle(MultipartFile file, String uploadPath, HttpServletRequest request) throws IOException {
        if (!"prod".equals(profile.getProfileName())) {
            throw new ApiException(ExceptionEnum.ACCESS_DENIED_EXCEPTION.setMessage("Only in the prod"));
        }
        /** 폴더 없으면 생성 */
        File uploadFolder = new File(uploadPath);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        /** 파일 생성 */
        String fileName = UUID.randomUUID() + file.getOriginalFilename();
        String filePath = uploadPath + fileName;
        File dest = new File(filePath);
        file.transferTo(dest);

        String domainURL = request.getRequestURL().toString().replace(request.getRequestURI(), "");

        /** 업로드 결과 */
        UploadedDTO uploadedDTO = UploadedDTO.builder()
                .uploaded(1) /** 성공 여부 */
                .fileName(fileName) /** 파일 이름 */
                .url(String.format("%s%s%s", domainURL, "/uploaded/", fileName)) /** 업로드 된 URL */
                .build();

        return uploadedDTO;
    }

}
