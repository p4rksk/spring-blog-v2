package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String content;
        private String username;

        public Board toEntity(){//DTO를 ENTITY로 바꾸는 메서드
            return new Board(title,content,username);
        }
    }
}
