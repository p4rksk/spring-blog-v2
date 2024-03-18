package shop.mtcoding.blog.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


//자동 컴퍼넌트 스캔 됨
public interface UserJPARepository extends JpaRepository<User, Integer> {

    //@Query("select u from User u where u.username = :username and u.password = :password") //복잡한 동적 쿼리는 여기서 못적는다.
    User findByUsernameAndPassword(@Param("username")String username, @Param("password")String password);
}
