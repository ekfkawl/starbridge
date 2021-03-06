package kr.starbridge.web.global.common.response;

import kr.starbridge.web.global.common.enums.ExceptionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 예외 처리 핸들러
 */
@RestControllerAdvice("kr.starbridge.web.domain")
public class ApiExceptionAdvice {

    /**
     * ApiException 예외 처리 핸둘러
     * @param e
     * @return
     */
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<ApiExceptionEntity> handlerApiException(final ApiException e) {
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ApiExceptionEntity.builder()
                        .code(e.getError().getCode())
                        .message(e.getError().getMessage())
                        .build());
    }

    /**
     * @Valid 예외 처리 핸들러
     * @param e
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiExceptionEntity> handlerMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiExceptionEntity.builder()
                        .code(ExceptionEnum.RUNTIME_EXCEPTION.getCode())
                        .message(errorMessage)
                        .build());

    }
}