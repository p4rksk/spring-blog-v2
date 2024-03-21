package shop.mtcoding.blog.user;

import lombok.Data;

import java.util.Optional;

public class UserRequest {

    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String email;

        public User toEntity(Optional<User> userOP){
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();

        }
    }

    @Data
    public static class LoginDTO {
        private String username;
        private String password;
    }

    @Data
    public static class UpdateDTO {
        private String password;
        private String email;
    }
}
