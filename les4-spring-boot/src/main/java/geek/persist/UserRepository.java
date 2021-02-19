package geek.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    List<User> findUserByUsernameLike(String username);

    @Query("select u from User u where u.username like concat('%',:username,'%') ")
    List<User> findUserByUsernameLike(@Param("username") String username);
}
