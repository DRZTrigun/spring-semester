package geek.store;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;


class ProductRepositoryTest {

    private static ProductRepository productRepository;

    @BeforeClass
    static void beforeClass(EntityManagerFactory emFactory){
        productRepository = new ProductRepository(emFactory);
    }

    @Before
    public void setUp() throws Exception{
    }

    @Test
    public void findAll() {
        
    }

    @Test
    void findById(Long id) {
    }


    @Test
    void insert(Product product) {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}