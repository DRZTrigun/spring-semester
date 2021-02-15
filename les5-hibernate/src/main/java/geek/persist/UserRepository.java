package geek.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public class UserRepository {

    private final EntityManagerFactory emFactory;

    public UserRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<User> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<User> from_user = em.createNamedQuery("allUsers", User.class).getResultList();
        em.close();
        return from_user;
    }

    public List<User> findAllLaymbda(){
        return executeForEntityManager(em -> em.createQuery("from Product", User.class).getResultList());
    }

    public List<User> findAllWork() {
        EntityManager em = emFactory.createEntityManager();
        try{
            return em.createNamedQuery("allUsers", User.class).getResultList();
        }finally {
            if (em != null){
                em.close();
            }
        }
    }

    public User findById(long id){
        EntityManager em = emFactory.createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    public User findByIdWork(long id){
        EntityManager em = emFactory.createEntityManager();
        try{
            return em.find(User.class, id);
        }finally {
            if (em != null){
                em.close();
            }
        }
    }

    public User findByIdLaymbda(long id){
        return executeForEntityManager(em -> em.find(User.class, id));
    }

    public void insert(User user){
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public void insertWork(User user){
        EntityManager em = emFactory.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        }catch (Exception exception){
            em.getTransaction().rollback();
        }finally {
            if (em != null){
                em.close();
            }
        }
    }

    public void insertfindByIdLaymbda(User user){
        executeInTransaction(em -> em.persist(user));
    }

    public void update(User user){
        EntityManager em = emFactory.createEntityManager();
        em.createQuery("from User u where u.username=:username").setParameter("username", user);
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }

    public void updateWork(User user){
        EntityManager em = emFactory.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        }catch (Exception exception) {
            em.getTransaction().rollback();
        }finally {
            if (em != null){
                em.close();
            }
        }
    }

    public void updateLaymbda(User user){
        executeInTransaction(em -> em.merge(user));
    }

    public void delete(long id){
        EntityManager em = emFactory.createEntityManager();
        User user = em.find(User.class, id);
        if (user != null) {
            em.getTransaction().begin();
            em.remove(id);
            em.getTransaction().commit();
        }
        em.close();
    }

    public void deleteWork(long id){
        EntityManager em = emFactory.createEntityManager();
        try{
            User user = em.find(User.class, id);
            if (user != null) {
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
            User user = em.find(User.class, id);
            if (user != null) {
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
