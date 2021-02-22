package geek.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserRepr> findAll();

    Page<UserRepr> findWithFilter(String usernameFilter, Integer minAge, Integer maxAge,
                                  Integer page, Integer size, Sort sort);

    Optional<UserRepr> findById(long id);

    void save(UserRepr user);

    void delete(long id);

}