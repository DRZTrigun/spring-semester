package geek;

import geek.persist.Contact;
import geek.persist.User;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class MainUserAndContact {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

//        // INSERT for one to many
//        em.getTransaction().begin();
//        User user = new User("user5", "password5", "user5@mail.cc");
//
//        em.persist(user);
//
//        List<Contact> contacts = new ArrayList<>();
//        contacts.add(new Contact("home phone", "(095)155-12-46", user));
//        contacts.add(new Contact("work phone", "(095)344-12-75", user));
//        contacts.add(new Contact("mobile phone", "(916)432-52-21", user));
//        contacts.add(new Contact("adress", "Russia, Moscow, Tverskaja", user));
//
//        contacts.forEach(em::persist);
//
//        em.getTransaction().commit();

        // SELECT for one to many
//        User user = em.find(User.class, 6L);
//        user.getContacts().forEach(System.out::println);
//
//        List<Contact> contacts = em.createQuery(
//                "select c from User u " +
//                "inner join Contact c on u.id = c.user.id" +
//                " where c.type = 'mobile phone'", Contact.class)
//                .getResultList();
//
//        contacts.forEach(System.out::println);
//
//        List<String> usernames = em.createQuery(
//                "select new  java.lang.String(u.username) from User u " +
//                        "inner join Contact c on u.id = c.user.id " +
//                        "where c.type = 'mobile phone'", String.class)
//                .getResultList();
//
//        System.out.println(usernames);

        em.close();
    }
}
