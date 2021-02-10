package geek.store;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProductRepository {

    private final EntityManagerFactory emFactory;

    public ProductRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<Product> findAll(){
        EntityManager em = emFactory.createEntityManager();
        List<Product> from_product = em.createQuery("from Product", Product.class).getResultList();
        em.close();
        return from_product;
    }

    public Product findById(long id){
        EntityManager em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();
        return product;
    }

    public void insert(Product product){
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public void update (Product product){
        EntityManager em = emFactory.createEntityManager();
        em.createQuery("from Product p where p.productname=:productname").setParameter("productname", product);
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(long id){
        EntityManager em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        if (product != null) {
            em.getTransaction().begin();
            em.remove(id);
            em.getTransaction().commit();
        }
        em.close();
    }

}
