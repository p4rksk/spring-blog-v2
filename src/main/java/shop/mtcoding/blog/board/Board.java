package shop.mtcoding.blog.board;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.util.MyDateUtil;

import java.sql.Timestamp;

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


    @ManyToOne//연관 관계로 본다
    private User user; //user_id  변수명_

    @CreationTimestamp//ps를 -> db로 갈때 날짜로 주입 됨
    private Timestamp createdAt;

    public String getTime(){
        return MyDateUtil.timestampFormat(createdAt);
    }

    


}
