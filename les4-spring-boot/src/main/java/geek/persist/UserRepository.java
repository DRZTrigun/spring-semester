package geek.persist;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query("select u from User u " +
            "where (u.username like concat('%',:username,'%') or :username is null) and " +
            "(u.age >= :minAge or :minAge is null) and " +
            "(u.age <= :maxAge or :maxAge is null)" +
            "order by u.id, u.username, u.age")
    List<User> findWithFilter(@Param("username") String usernameFilter,
                              @Param("minAge") Integer minAge,
                              @Param("maxAge") Integer maxAge,
                              @Param("sort") Sort sort);

}
