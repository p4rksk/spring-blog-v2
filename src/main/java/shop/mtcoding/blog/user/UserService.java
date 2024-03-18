package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.erros.exception.Exception400;
import shop.mtcoding.blog._core.erros.exception.Exception401;

import java.util.Optional;

@RequiredArgsConstructor
@Service //IOC 등록
public class UserService {

    private final UserJPARepository userJPARepository;

    public User 로그인(UserRequest.LoginDTO reqDTO){
       User sessionUser = userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));//회원가입 때 DB에 없는 값을 넣으면 null로 돌려준다.
        return sessionUser;
    }

    @Transactional
    public void 회원가입(UserRequest.JoinDTO reqDTO){
        //1. 유효성 검사 (컨트롤러 책임)

        //2. 유저네임 중복검사(서비스 체크)
        Optional<User> userOp =userJPARepository.findByUsername(reqDTO.getUsername());

        if (userOp.isPresent()){//동일한 아이디가 있으면 잘못됐다
            throw new Exception400("중복된 유저네임입니다.");
        }

        userJPARepository.save(reqDTO.toEntity());
    }
}
