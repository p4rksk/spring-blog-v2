package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.erros.exception.Exception403;
import shop.mtcoding.blog._core.erros.exception.Exception404;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepository;



    public Board 글조회(int boardId){
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(()->new Exception404("게시글을 찾을 수 없습니다"));


        return board;
    }



    @Transactional
    public void 글수정(int boardId, int sessionUserId, BoardRequest.updateDTO reqDTO){
        //1.조회 및 예외처리
       Board board =  boardJPARepository.findById(boardId)
               .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
       //2. 권한 처리
        if(sessionUserId != board.getUser().getId()){
            throw new Exception403("게시글을 수정할 권한이 없습니다");
        }
    }

    @Transactional
    public String 글쓰기 (BoardRequest.SaveDTO reqDTO, User sessionUser){
        boardJPARepository.save(reqDTO.toEntity(sessionUser));
        return "redirect:/";

    }

    @Transactional
    public void 글삭제(Integer boardId, Integer sessionUserId) {
        Board board= boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        if (sessionUserId != board.getUser().getId()){
            throw new Exception403("게시글을 삭제할 권한이 없습니다");
        }

        boardJPARepository.deleteById(boardId);
    }
}
