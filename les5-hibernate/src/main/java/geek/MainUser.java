package geek;

import geek.persist.User;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MainUser {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

//        //INSERT
//        em.getTransaction().begin();
//        User user1 = new User("user1", "password1", "mail1@mail.cc");
//        User user2 = new User("user2", "password2", "mail2@mail.cc");
//        User user3 = new User("user3", "password3", "mail3@mail.cc");
//        User user4 = new User("user4", "password4", "mail4@mail.cc");
//        User user5 = new User("user5", "password5", "mail5@mail.cc");
//        em.persist(user1);
//        em.persist(user2);
//        em.persist(user3);
//        em.persist(user4);
//        em.persist(user5);
//
//        em.getTransaction().commit();
//        em.close();

//        //SELECT
//        System.out.println("User with id 1");
//        User user = em.find(User.class, 1L);
//        System.out.println(user);
//
//        // HQL, JPQL
//        System.out.println("All users in table");
//        List<User> userList = em.createQuery("from User", User.class)
//                .getResultList();
//        System.out.println(userList);
//
//        System.out.println("User with name 'user3'");
//        Object user3 = em.createQuery("from User u where u.username = :username")
//                .setParameter("username", "user3")
//                .getSingleResult();
//        System.out.println(user3);
//
//        // SQL
//        userList = em.createNativeQuery("select * from users", User.class)
//                .getResultList();
//        System.out.println(userList);
//
//        em.createNamedQuery("userByName")
//                .setParameter("username", "user3")
//                .getSingleResult();
//        em.close();

//        //UPDATE
//        User user = em.find(User.class, 1L);
//
//        em.getTransaction().begin();
//
//        user.setPassword("adminLOL");
//
//        em.getTransaction().commit();
//
//        List<User> userList = em.createQuery("from User", User.class)
//                .getResultList();
//        System.out.println(userList);
//
//        em.close();

//       // DELETE
//        em.getTransaction().begin();
//
////        User user = em.find(User.class, 5L);
////        if (user != null) {
////            em.remove(user);
////        }
//
//        em.createQuery("delete  from User where username=:username")
//                .setParameter("username", "user4")
//                .executeUpdate();
//
//        em.getTransaction().commit();
//
//        List<User> userList = em.createQuery("from User", User.class)
//                .getResultList();
//        System.out.println(userList);
//
//        em.close();
    }
}
