package geek.store;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

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

    public List<Product> findAllLaymbda(){
       return executeForEntityManager(em -> em.createQuery("from Product", Product.class).getResultList());
    }

    public Product findById(long id){
        EntityManager em = emFactory.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();
        return product;
    }

    public Product findByIdLaymbda(long id){
        return executeForEntityManager(em -> em.find(Product.class, id));
    }

    public void insert(Product product){
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public void insertWork(Product product){
        EntityManager em = emFactory.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(product);
            em.getTransaction().commit();
        }catch (Exception exception){
            em.getTransaction().rollback();
        }finally {
            if (em != null){
                em.close();
            }
        }
    }

    public void insertfindByIdLaymbda(Product product){
        executeInTransaction(em -> em.persist(product));
    }


    public void update (Product product){
        EntityManager em = emFactory.createEntityManager();
        em.createQuery("from Product p where p.productname=:productname").setParameter("productname", product);
        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();
        em.close();
    }

    public void updateWork(Product product){
        EntityManager em = emFactory.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(product);
            em.getTransaction().commit();
        }catch (Exception exception) {
            em.getTransaction().rollback();
        }finally {
            if (em != null){
                em.close();
            }
        }
    }

    public void updateLaymbda(Product product){
        executeInTransaction(em -> em.merge(product));
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

    public void deleteWork(long id){
        EntityManager em = emFactory.createEntityManager();
        try{
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.getTransaction().begin();
                em.remove(id);
                em.getTransaction().commit();
            }
        } catch (Exception exception) {
            em.getTransaction().rollback();
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    public void deleteLaymbda(long id){
        executeInTransaction(em -> {
            Product product = em.find(Product.class, id);
            if (product != null) {
                em.remove(id);
            }
        });
    }

    private <R> R executeForEntityManager(Function<EntityManager, R> function){
        EntityManager em = emFactory.createEntityManager();
        try {
            return  function.apply(em);
        } finally {
            if (em != null){
                em.close();
            }
        }
    }

    private void executeInTransaction(Consumer<EntityManager> consumer){
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            consumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception e){
            em.getTransaction().rollback();
        } finally {
            if (em != null){
                em.close();
            }
        }
    }
}