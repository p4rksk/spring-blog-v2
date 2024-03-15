package shop.mtcoding.blog.board;


import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;


@Import(BoardRepository.class)
@DataJpaTest
public class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void updateById_test(){
        //given
        int id = 1;
        String title = "title1";
        String content = "content1";

        //when



        //then

    }



    @Test
    public void randomquery_test(){
        int[] ids = {1,2};

        // select u from User u where u.id in (?,?);
        String q = "select u from User u where u.id in (";
        for (int i=0; i<ids.length; i++){
            if(i==ids.length-1){
                q = q + "?)";
            }else{
                q = q + "?,";
            }
        }
        System.out.println(q);
    }

    @Test
    public void findAll_custom_inquery_test() { //user.id 가 뭐 뭐 있는지 파악하기
        List<Board> boardList = boardRepository.findAll();

        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        for (int i : userIds){
            System.out.println(i);
        }

        // select * from user_tb where id in (3,2,1,1);
        // select * from user_tb where id in (3,2,1);
    }

    @Test
    public void findAll_lazyloding_test(){
        //given

        //when
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach( board -> {
            System.out.println(board.getUser().getUsername());
                });

        //then
    }


    @Test
    public void findAll_test(){
        //given

        //when
        List<Board> board = boardRepository.findAll();

        //then
    }

    @Test
    public void findById() {
        //given
        int id = 1;

        //when
        boardRepository.findById(id);
    }

    @Test
    public void findById_test() { //LazyLoding
        int id = 1;
        System.out.println("start - 1");
        Board board = boardRepository.findById(id);
        System.out.println("start - 2");
        System.out.println(board.getUser().getId());
        System.out.println("start - 3");
        System.out.println(board.getUser().getUsername());
    }
}


