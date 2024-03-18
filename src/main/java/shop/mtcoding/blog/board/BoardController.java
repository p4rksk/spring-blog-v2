package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id,HttpServletRequest request){
        Board board = boardService.글조회(id);
        request.setAttribute("board",board);
        return "board/update-form";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id){
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.글삭제(id, sessionUser.getId());
        return "redirect:/";
    }

    @GetMapping({ "/"})
    public String index(HttpServletRequest request){
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList",boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "/board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {

        User sessionUser = (User) session.getAttribute("sessionUser");
        Board board = boardRepository.findByIdJoinUser(id);

        //로그인을 하고 게시글의 주인이면 isOwner가 true가 된다.
        boolean isOwner = false;
        if(sessionUser != null) {
            if (sessionUser.getId() == board.getUser().getId()){
                isOwner = true;
            }
        }

        request.setAttribute("isOwner",isOwner);
        request.setAttribute("board",board);
        return "board/detail";
    }
}
