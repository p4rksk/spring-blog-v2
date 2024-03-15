package shop.mtcoding.blog.user;

import jakarta.persistence.NoResultException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.erros.exception.Exception400;
import shop.mtcoding.blog._core.erros.exception.Exception401;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserRepository userRepository;
    private final HttpSession session;


    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO reqDTO ){
        User sessionUser = (User) session.getAttribute("sessionUser");
        userRepository.updateById(sessionUser.getId(),reqDTO);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO){
        try {
            User sessionUser = userRepository.findByUsernameAndPassword(reqDTO);
            session.setAttribute("sessionUser", sessionUser);
            return "redirect:/";
        }catch (EmptyResultDataAccessException e){
            throw new Exception401("유저네임 혹은 비밀번호가 틀렸어요");
        }
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO){ //회원가입하면 바로 로그인 되는 시스템

        try {
            userRepository.save(reqDTO.toEntity());
        }catch (DataIntegrityViolationException e){
            throw new Exception400("동일한 유저네임이 존재합니다");
        }
        return "redirect:/";
    }

    @GetMapping("/join-form")
    public String joinForm() {
        return "/user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "/user/login-form";
    }

    @GetMapping("/user/update-form")
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        if (sessionUser == null){
            throw new Exception401("인증되지 않았어요. 로그인 되었어요");
        }

        User user = userRepository.findById(sessionUser.getId());
        request.setAttribute("user", user);
        return "/user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
