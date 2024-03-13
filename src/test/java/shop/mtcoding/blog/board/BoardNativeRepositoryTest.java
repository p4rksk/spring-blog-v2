package shop.mtcoding.blog.board;


public class BoardNativeRepositoryTest {

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
