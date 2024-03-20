package shop.mtcoding.blog.board;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table(name = "board_tb")
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String content;

    //@JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; // db -> user_id

    @CreationTimestamp // pc -> db (날짜주입)
    private Timestamp createdAt;

    @OrderBy("id desc")
    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)//양방향 매핑
    private List<Reply> replies = new ArrayList<>(); //댓글이 없으면 null 값이 들어가 터질 수 있어서 new 해서 댓글 박스를 만들어놓기

    @Transient // 테이블 생성이 안됨
    private boolean isBoardOwner;


    @Builder
    public Board(Integer id, String title, String content, User user, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;
    }
}