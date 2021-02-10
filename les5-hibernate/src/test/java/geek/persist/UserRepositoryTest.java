package geek.persist;

import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import java.util.List;


class UserRepositoryTest {

    private static UserRepository userRepository;

    @BeforeClass
    static void beforeClass(){

    }

    @Before
    public void initTest(EntityManagerFactory emFactory){
        userRepository = new UserRepository(emFactory);
        EntityManagerFactory emF = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
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