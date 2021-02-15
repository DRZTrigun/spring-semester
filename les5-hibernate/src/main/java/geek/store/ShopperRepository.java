package geek.store;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ShopperRepository {

    private final EntityManagerFactory emFactory;

    public ShopperRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    private List<Shopper> findAll(){
        return executeForEntityManager(em -> em.createQuery("from Shopper", Shopper.class).getResultList());
    }

    private Shopper findById(Long id){
        return executeForEntityManager(em -> em.find(Shopper.class , id));
    }

    public void insert(Shopper shopper){
        executeInTransaction(em -> em.persist(shopper));
    }

    public void update(Shopper shopper){
        executeInTransaction(em -> em.merge(shopper));
    }

    public void delete(Long id){
        executeInTransaction(em -> {
            if (id != null){
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
