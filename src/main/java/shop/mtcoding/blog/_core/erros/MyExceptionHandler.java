package shop.mtcoding.blog._core.erros;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import shop.mtcoding.blog._core.erros.exception.*;
import shop.mtcoding.blog._core.utils.ApiUtil;


//RuntimeException이 터지면 해당 파일로 오류가 모인다.
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)//어떤 오류가 터졌을 때 여기로 오는지 정하는 어노테이션
    public ResponseEntity<?> ex400(RuntimeException e){
        ApiUtil<?> apiUtil = new ApiUtil<>(400, e.getMessage()); // http body -> 구성한 객체

        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST);//http header, http body
    }

    @ExceptionHandler(Exception401.class)
    public ResponseEntity<?> ex401(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(401, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception403.class)
    public ResponseEntity<?> ex403(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(403, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> ex404(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(404, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception500.class)
    public ResponseEntity<?> ex500(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(500, e.getMessage());
        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

