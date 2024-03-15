package shop.mtcoding.blog._core.erros;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shop.mtcoding.blog._core.erros.exception.*;


//RuntimeException이 터지면 해당 파일로 오류가 모인다.
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)//어떤 오류가 터졌을 때 여기로 오는지 정하는 어노테이션
    public String ex400(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/400";
    }

    @ExceptionHandler(Exception401.class)//어떤 오류가 터졌을 때 여기로 오는지 정하는 어노테이션
    public String ex401(RuntimeException e,HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/401";
    }

    @ExceptionHandler(Exception403.class)//어떤 오류가 터졌을 때 여기로 오는지 정하는 어노테이션
    public String ex403(RuntimeException e,HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/403";
    }

    @ExceptionHandler(Exception404.class)//어떤 오류가 터졌을 때 여기로 오는지 정하는 어노테이션
    public String ex404(RuntimeException e,HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/404";
    }

    @ExceptionHandler(Exception500.class)//어떤 오류가 터졌을 때 여기로 오는지 정하는 어노테이션
    public String ex500(RuntimeException e,HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/500";
    }
}
