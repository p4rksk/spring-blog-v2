package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(BoardPersistRepository.class)
@DataJpaTest
public class BoardPersistRepositoryTest {

    @Autowired // IOC에 있는걸 DI 해주는 역할
    private BoardPersistRepository boardNativeRepository;


    @Autowired
    private BoardPersistRepository boardPersistRepository;

    @Test
    public void save_test(){
        //given
        Board board = new Board("제목5","내용5","ssar"); //비영속 객체

        //when
        Board boardPc = boardPersistRepository.save(board);//영속 객체
        System.out.println("save_test : "+boardPc);
    }

//    @Test
//    public void updateById_test(){
//        //given(파라미터에 들어 갈것들)
//        int id = 1;
//        String title = "제목 수정 1";
//        String content = "내용 수정 1";
//        String username = "bori";
//
//        //when(내가 하려는 내용)
//        boardNativeRepository.updateById(id,title,content,username);
//
//
//        //then(검증 단계)
//        Board board = boardNativeRepository.findById(id);
//        assertThat(board.getUsername()).isEqualTo("bori");
//        assertThat(board.getTitle()).isEqualTo("제목 수정 1");
//        assertThat(board.getContent()).isEqualTo("내용 수정 1");
//    }
//
//
//    @Test
//    public void deleteById_test(){
//        //given
//        int id = 1;
//
//        //when
//        boardNativeRepository.deleteById(id);
//
//
//        //then
//        List<Board> boardList = boardNativeRepository.findAll();
//        assertThat(boardList.size()).isEqualTo(3);
//    }
//
    @Test
    public void findAll_test(){
        //given

        //when
        List<Board> boardList = boardPersistRepository.findAll();

        //then
        System.out.println("findAll_test/size : " + boardList.size());
        System.out.println("findAll_test/username : " + boardList.get(2).getUsername());

        //org.assertj.core.api(이걸로 태스트 확인하기)
        assertThat(boardList.size()).isEqualTo(4);
        assertThat(boardList.get(2).getUsername()).isEqualTo("ssar");
    }

    @Test
    public void findById(){
        // given
        int id = 1;

        //when
        Board board =boardPersistRepository.findById(id); //1차 캐쉬가 된다.
        boardPersistRepository.findById(id);

    }
//
//    @Test
//    public void findById_test(){
//        //given
//        int id = 1;
//
//        //when
//        Board board = boardNativeRepository.findById(id);
//        System.out.println("findById_test "+board);
//
//        //then
//        assertThat(board.getTitle()).isEqualTo("제목1");
//        assertThat(board.getContent()).isEqualTo("내용2");
//    }

}
