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

    public Board findById(int id){
        Board board = em.find(Board.class,id); //PC에 띄어져있는 BOARD에 프라이머리키를 찾는다.
        return board;
    }


    @Transactional
    public void updateById(int id ,String title, String content, String username){
        Query query = em.createNativeQuery("update board_tb set title=?, content=?, username=? where id =?");
        query.setParameter(1,title);
        query.setParameter(2,content);
        query.setParameter(3,username);
        query.setParameter(4,id);

        query.executeUpdate();
    }

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

    @Transactional
    public void deleteById(int id){
        Query query = em.createNativeQuery("delete from board_tb where id =?");
        query.setParameter(1,id);


        query.executeUpdate();
    }
}
