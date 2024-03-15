package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog.board.Board;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

//    @Transactional
//    public User save2(String username, String password, String email){
//        Query query = em.createNativeQuery("insert into user_tb(username, password, email, created_at) values(?, ?, ?, now())");
//        query.setParameter(1, username);
//        query.setParameter(2,password);
//    }

    @Transactional
    public User save(User user){
        em.persist(user);
        return user;
    }


    public void updateById(int id, String title, String content){

    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO reqDTO){
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password" ,User.class);
        query.setParameter("username",reqDTO.getUsername());
        query.setParameter("password",reqDTO.getPassword());
        return (User) query.getSingleResult();
    }
}
