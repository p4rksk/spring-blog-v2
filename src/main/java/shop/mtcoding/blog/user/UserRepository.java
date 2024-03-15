package shop.mtcoding.blog.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public User updateById(int id, UserRequest.UpdateDTO reqDTO){
        User user = findById(id);
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());
        return user;
    }


    public User findById(int id){
        //id, title, content, user_id(이질감), created_at;
        User user = em.find(User.class, id);
        return user;
    }

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


    public User findByUsernameAndPassword(UserRequest.LoginDTO reqDTO){
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password" ,User.class);
        query.setParameter("username",reqDTO.getUsername());
        query.setParameter("password",reqDTO.getPassword());
        return (User) query.getSingleResult();
    }

    public User findByUsername(String username){
        Query query = em.createQuery("select u from User u where u.username = :username" ,User.class);
        query.setParameter("username",username);
        return (User) query.getSingleResult();
    }
}
