package shop.mtcoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board,Integer> {//crud를 제공해줌 없는것들으 밑에서 코드를 짜야됨

    @Query("select new shop.mtcoding.blog.board.BoardResponse$CountDTO(b.id, b.title, b.content, b.user.id, (select count(r.id) from Reply r where r.board.id = b.id)) from Board b")
    List<BoardResponse.CountDTO> findAllWithReplyCount();


    @Query("select b from Board b join fetch b.user u where b.id = :id")
    Optional<Board> findByIdJoinUser(@Param("id")int id); // 이렇게 짜는 것들이 JPARepository가 제공을 안하는 것들
    // 복잡한것들은 기본 repository에서 직접 짜자 (예시코드 참고)

    @Query("select b from Board b join fetch b.user u left join fetch b.replies r where b.id = :id")
    Optional<Board> findByIdJoinUserAndReplies(@Param("id")int id);
}
