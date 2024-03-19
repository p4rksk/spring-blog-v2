package shop.mtcoding.blog.board;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.erros.exception.Exception403;
import shop.mtcoding.blog._core.erros.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepository;

    // board, isOwner
    public Board 글상세보기(int boardId, User sessionUser) {
        Board board = boardJPARepository.findByIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        boolean isBoardOwner = false;
        if(sessionUser != null){
            if(sessionUser.getId() == board.getUser().getId()){
                isBoardOwner = true;
            }
        }

        board.setBoardOwner(isBoardOwner);

        board.getReplies().forEach(reply -> {
            boolean isReplyOwner = false;

            if(sessionUser != null){
                if(reply.getUser().getId() == sessionUser.getId()){
                    isReplyOwner = true;
                }
            }
            reply.setReplyOwner(isReplyOwner);
        });



        return board;
    }



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

    public List<Board> 글목록조회(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        return boardJPARepository.findAll(sort);
    }

//    public void 글상세보기(Integer boardId, User sessionUser) {
//        Board board = boardJPARepository.findByIdJoinUser(boardId)
//                .orEl
//
//        //로그인을 하고 게시글의 주인이면 isOwner가 true가 된다.
//        boolean isOwner = false;
//        if(sessionUser != null) {
//            if (sessionUser.getId() == board.getUser().getId()){
//                isOwner = true;
//            }
//        }


}

