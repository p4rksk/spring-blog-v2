package shop.mtcoding.blog.reply;

import lombok.Data;

public class ReplyResponse {


    @Data
    public static class saveDTO{
        private Integer boardId;
        private String username;
        private String comment;

        public saveDTO(Reply reply) {
            this.boardId = reply.getBoard().getId();
            this.username = reply.getUser().getUsername();
            this.comment = reply.getComment();
        }
    }
}
