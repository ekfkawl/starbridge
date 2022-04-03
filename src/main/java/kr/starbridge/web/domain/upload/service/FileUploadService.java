package kr.starbridge.web.domain.upload.service;

import kr.starbridge.web.domain.upload.dto.UploadedDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadService {

    /**
     * 파일 업로드
     * @param file
     * @param uploadPath
     * @return
     * @throws IOException
     */
    public UploadedDTO uploadSingle(MultipartFile file, String uploadPath) throws IOException {
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

        /** 업로드 결과 */
        UploadedDTO uploadedDTO = UploadedDTO.builder()
                .uploaded(1) /** 성공 여부 */
                .fileName(fileName) /** 파일 이름 */
                .refUploadPath(filePath) /** 업로드 된 URL */
                .build();

        return uploadedDTO;
    }
}
