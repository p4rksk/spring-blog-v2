package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog.user.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Repository
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAllV2() {
        Query q1 = em.createQuery("select b from Board b order by b.id desc", Board.class);
        List<Board> boardList = q1.getResultList();

        Set<Integer> userIds = new HashSet<>();
        for (Board board : boardList){
            userIds.add(board.getUser().getId());
        }

        Query q2 = em.createQuery("select u from User u where u.id in :userIds", User.class);
        q2.setParameter("userIds", userIds);
        List<User> userList = q2.getResultList();

        for (Board board : boardList){
            for (User user : userList) {
                if (user.getId() == board.getUser().getId()){
                    board.setUser(user);
                }
            }
        }

        return boardList;
    }

    public List<Board> findAll(){
        Query query  = em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

    public Board findByIdJoinUser(int id){
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }

    public Board findById(int id){
        //id, title, content, user_id(이질감), created_at;
        Board board = em.find(Board.class, id);
        return board;
    }
}
