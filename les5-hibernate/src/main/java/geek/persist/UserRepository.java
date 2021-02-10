package geek.persist;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;


public class UserRepository {

    private final EntityManagerFactory emFactory;

    public UserRepository(EntityManagerFactory emFactory) {
        this.emFactory = emFactory;
    }

    public List<User> findAll() {
        EntityManager em = emFactory.createEntityManager();
        List<User> from_user = em.createQuery("from User", User.class).getResultList();
        em.close();
        return from_user;
    }

    public User findById(long id){
        EntityManager em = emFactory.createEntityManager();
        User user = em.find(User.class, id);
        em.close();
        return user;
    }

    public void insert(User user){
        EntityManager em = emFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public void update(User user){
        EntityManager em = emFactory.createEntityManager();
        em.createQuery("from User u where u.username=:username").setParameter("username", user);
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
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
}
