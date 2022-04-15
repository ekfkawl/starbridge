package kr.starbridge.web.domain.bridge.controller;

import com.mysql.cj.util.StringUtils;
import kr.starbridge.web.domain.bridge.dto.CommonImportDTO;
import kr.starbridge.web.domain.bridge.dto.IpDTO;
import kr.starbridge.web.domain.bridge.entity.IpEntity;
import kr.starbridge.web.domain.bridge.enums.FunctionURIEnum;
import kr.starbridge.web.domain.bridge.service.IpService;
import kr.starbridge.web.domain.member.dto.MemberDTO;
import kr.starbridge.web.global.common.enums.ExceptionEnum;
import kr.starbridge.web.global.common.response.ApiException;
import kr.starbridge.web.global.common.response.ApiResult;
import kr.starbridge.web.global.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static kr.starbridge.web.domain.bridge.mapper.IpMapper.toIpDTO;
import static kr.starbridge.web.domain.bridge.mapper.IpMapper.toIpEntity;

/**
 * 아이피 블랙리스트 컨트롤러
 */
@RestController
@RequiredArgsConstructor
public class BridgeIpController extends BridgeBaseController {

    private final IpService ipService;

    /**
     * 등록/수정
     * @param ipDTO
     * @return
     */
    @PostMapping("/api/hash")
    public ApiResult<IpDTO> apiUpsertHash(@Valid @RequestBody IpDTO ipDTO) {
        MemberDTO memberDTO = SecurityUtils.getSelfInfo();
        ipDTO.getId().setMemberId(memberDTO.getId());

        IpEntity ipEntity = ipService.upsertHash(toIpEntity(ipDTO));

        return new ApiResult(toIpDTO(ipEntity));
    }

    /**
     * 조회
     * @param id
     * @return
     */
    @GetMapping("/api/hash")
    public ApiResult<List<IpDTO>> apiGetHashes(@RequestParam String id) {
        return new ApiResult<>(toIpDTO(ipService.getHashesEx(id)));
    }

    /**
     * 삭제
     * @param ipDTO
     * @return
     */
    @DeleteMapping("/api/hash")
    public ApiResult<IpDTO> apiDeleteHash(@RequestBody IpDTO ipDTO) {

        if (StringUtils.isNullOrEmpty(ipDTO.getId().getHash())) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }

        MemberDTO memberDTO = SecurityUtils.getSelfInfo();
        ipDTO.getId().setMemberId(memberDTO.getId());

        ipService.delete(toIpEntity(ipDTO));

        return new ApiResult<>(ipDTO);
    }

    /**
     * 블랙리스트 추출 대상 수정
     * @param ipDTO
     * @return
     */
    @PutMapping("/api/hash-export")
    public ApiResult<List<IpDTO>> apiUpdateHashExport(@Valid @RequestBody IpDTO ipDTO) {
        MemberDTO memberDTO = SecurityUtils.getSelfInfo();
        ipDTO.getId().setMemberId(memberDTO.getId());

        IpEntity ipEntity = ipService.updateHashExport(toIpEntity(ipDTO));

        return new ApiResult(toIpDTO(ipEntity));
    }

    /**
     * 블랙리스트 임포트
     * @param optionalCommonImportDTO
     * @return
     */
    @PostMapping("/api/hash-import")
    public ApiResult<List<IpDTO>> apiHashImport(@RequestBody Optional<CommonImportDTO> optionalCommonImportDTO) {

        if (!optionalCommonImportDTO.isPresent()) {
            throw new ApiException(ExceptionEnum.RUNTIME_EXCEPTION);
        }
        CommonImportDTO commonImportDTO = optionalCommonImportDTO.get();

        MemberDTO memberDTO = SecurityUtils.getSelfInfo();

        List<IpEntity> ipEntities = ipService.importHashes(memberDTO.getId(), toIpEntity(commonImportDTO));

        return new ApiResult<>(toIpDTO(ipEntities), null, FunctionURIEnum.Url(FunctionURIEnum.URI_BLACKLIST_IP));
    }
}
