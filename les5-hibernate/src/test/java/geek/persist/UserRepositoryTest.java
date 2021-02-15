package geek.persist;

import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;


class UserRepositoryTest {

    private static UserRepository userRepository;

    @BeforeClass
    static void beforeClass(){

    }

    @Before
    public void initTest(EntityManagerFactory emFactory){
        User user1 = new User("user1","user1","user1@mail.user");
        User user2 = new User("user2","user2","user2@mail.user");
        User user3 = new User("user3","user3","user3@mail.user");
        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userRepository = new UserRepository((EntityManagerFactory) userList);
    }

    @Test
    public void findAll() throws Exception {
        List<User> userList = userRepository.findAll();
        System.out.println(userList);
    }

    @Test
    void findById(long id) {

    }

    @Test
    void insert() {
    }

    @Test
    void update(User user) {
        user = userRepository.findById(3);
        user.setPassword("LOLAdmin");

        System.out.println();
    }

    @Test
    void delete() {
    }
}