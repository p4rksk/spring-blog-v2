package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {
    private final EntityManager em;

    @Transactional
    public void updateById(int id, BoardRequest.UpdateDTO reqDTO){
        Board board = findById(id);
        board.update(reqDTO);
    }//더티 체킹


    public Board findById(int id){
        Board board = em.find(Board.class,id); //PC에 띄어져있는 BOARD에 프라이머리키를 찾는다.
        return board;
    }


//    @Transactional
//    public void updateById(int id ,String title, String content, String username){
//        Query query = em.createNativeQuery("update board_tb set title=?, content=?, username=? where id =?");
//        query.setParameter(1,title);
//        query.setParameter(2,content);
//        query.setParameter(3,username);
//        query.setParameter(4,id);
//
//        query.executeUpdate();
//    }

//    public Board findById(int id){
//        Query query = em.createNativeQuery("select * from board_tb where id =? ", Board.class);
//        query.setParameter(1,id);
//        return (Board) query.getSingleResult();
//    }

    public List<Board> findAll(){
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class); //desc는 내림차순
        return query.getResultList();

    }

    @Transactional
    public Board save(Board board){
        //비영속 객체
        em.persist(board);

        //board -> 영속 객체
        return board;
    }

    // 직접 조회
    @Transactional
    public void deleteByIdV2(int id) {
        Board board = findById(id);
        em.remove(board); // PC에 객체 지우고, (트랜잭션 종료 시) 삭제 쿼리를 전송함
    } // 시작 begin() / 종료 성공 commit() 실패 rollback()

    // 쿼리문 직접 작성
    @Transactional
    public void deleteByIdV1(int id) {
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }
}
