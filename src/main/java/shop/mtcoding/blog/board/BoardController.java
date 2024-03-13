package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardPersistRepository BoardPersistRepository;

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id, String title, String content, String username){
        BoardPersistRepository.updateById(id,title,content,username);
        return "redirect:/board/"+id;
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id,HttpServletRequest request){
        Board board = BoardPersistRepository.findById(id);
        request.setAttribute("board",board);
        return "board/update-form";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id){
        BoardPersistRepository.deleteByIdV2(id);
        return "redirect:/";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO){
        BoardPersistRepository.save(reqDTO.toEntity());
        return"redirect:/";
    }

    @GetMapping({ "/"})
    public String index(HttpServletRequest request){

        List<Board>boardList = BoardPersistRepository.findAll();
        request.setAttribute("boardList",boardList);

        return "index"; //서버가 내부적으로 index 페이지를 찾아서 가기때문에 요청이 두번일어난거다.(내부적으로 requestDispatcher가 일어난거다)
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "/board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Board board =  BoardPersistRepository.findById(id);
        request.setAttribute("board",board);
        return "board/detail";
    }
}
