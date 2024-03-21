package shop.mtcoding.blog.board;
import lombok.AllArgsConstructor;
import lombok.Data;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.util.ArrayList;
import java.util.List;


public class BoardResponse {



    @AllArgsConstructor
    @Data
    public static class CountDTO {
        private Integer id;
        private String title;
        private String content;
        private Integer userId;
        private Long replyCount;
    }

    //게시글 쓰기 화면
    @Data
    public static class DTO {
        private int id;
        private String title;
        private String content;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }

    // 게시글 상세보기 화면
    @Data
    public static class mainDTO {
        private int id;
        private String title;

        public mainDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }


    // 게시글 목록 보기 화면
    @Data
    public static class DetailDTO{
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username;
        private List<ReplyDTO>replies =new ArrayList<>();//영속화된 객체를 넣으면 lazyloding이 걸린다 그러므로 빈배열로 생성하기
        private boolean isOwner;

        public DetailDTO(Board board, User sessionUser ) {//매개변수에 DB에서 조호된 엔티티를 건다.
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername(); // 조인해서 가져옴
            this.isOwner = false;

            if(sessionUser != null){
                if (sessionUser.getId() == userId) isOwner = true;
            }

            this.replies = board.getReplies().stream().map(reply -> new ReplyDTO(reply,sessionUser)).toList();
            //물가에 기존 데이터를 던져서 dto의 데이터를 변환 시켜준다.
        }

        @Data
        public class ReplyDTO{
            private int id;
            private String comment;
            private int userId; //댓글 작성자 아이디
            private String username; // 댓글 작성자 이름
            private boolean isOwner;

            public ReplyDTO(Reply reply, User sessionUser) {
                this.id = reply.getId();
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername();
                this.isOwner = false;

                if(sessionUser != null){
                    if (sessionUser.getId() == userId) isOwner = true;
                }
            }
        }
    }

}