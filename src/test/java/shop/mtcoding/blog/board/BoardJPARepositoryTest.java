package shop.mtcoding.blog.board;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findAllWithReplyCount_test() throws JsonProcessingException {
        // given

        // when
        List<BoardResponse.CountDTO> respDTO = boardJPARepository.findAllWithReplyCount();
        // then
        System.out.println(respDTO);
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(respDTO);
        System.out.println("json = " + json);
    }

    @Test
    public void findByIdJoinUserAndReplies_test(){
        //given
        int id = 4;

        //when
        Board board = boardJPARepository.findByIdJoinUserAndReplies(id).get();


        //then
    }

    //save
    @Test
    public void save_test() {
        //given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("제목5")
                .content("내용5")
                .user(sessionUser)
                .build();

        //when
        boardJPARepository.save(board);

        //then
    }

    //findById
    @Test
    public void findById_test(){
        //given
        int id = 1;
        //when
        Optional<Board> boardOP = boardJPARepository.findById(id);

        if (boardOP.isPresent()){
            Board board = boardOP.get();
            System.out.println("findById_test : "+board.getTitle());
        }
        //then
    }

    //findByIdJoinUser
    @Test
    public void findByIdJoinUser_test(){
        //given
        int id = 1;
        //when
        Board board  = boardJPARepository.findByIdJoinUser(id).get();

        //then
        System.out.println("findByIdJoinUser_test : "+board.getTitle());
        System.out.println("findByIdJoinUser_test : "+board.getUser().getUsername());
    }

    //findAll(sort)
    @Test
    public void findAll_test(){
        //given
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        //when
        List<Board> boardList = boardJPARepository.findAll(sort);
        //then
        System.out.println("findAll_test : "+boardList);
    }

    //deleteById
    @Test
    public void delete_test(){
        //given
        int id = 1;

        //when
        boardJPARepository.deleteById(id);

        //then
        em.flush();

    }
}
