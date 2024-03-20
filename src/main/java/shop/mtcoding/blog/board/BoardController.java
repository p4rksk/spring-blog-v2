package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.erros.exception.Exception403;
import shop.mtcoding.blog._core.erros.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final HttpSession session;

    // TODO :글 목록 조회 API 필요
    // TODO :글 조회 API 필요
    // TODO :글 상세보기 API 필요

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글쓰기(reqDTO, sessionUser);

        return"redirect:/";
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id,BoardRequest.updateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board= boardRepository.findById(id);

        if (sessionUser.getId() != board.getUser().getId()){
            throw new Exception403("게시글을 수정할 권한이 없습니다");
        }

        boardRepository.updateById(id, reqDTO.getTitle(), reqDTO.getContent());
        return "redirect:/board/"+id;
    }



    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id){
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id, sessionUser.getId());
        return "redirect:/";
    }


}
