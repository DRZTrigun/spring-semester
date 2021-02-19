package geek;

import geek.store.Product;
import geek.store.ProductInformation;
import geek.store.Shopper;
import geek.store.ShopperRepository;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MainProductAndShopper {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernateproduct.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        // INSERT
        em.getTransaction().begin();
        Shopper shopper1 = new Shopper("shopper1", "shopper1");
        em.persist(shopper1);
        Shopper shopper2 = new Shopper("shopper2", "shopper2");
        em.persist(shopper2);
        Shopper shopper3 = new Shopper("shopper3", "shopper3");
        em.persist(shopper3);


//        Product product = new Product("apple", 35.30);
//        em.persist(product);
//        ProductInformation productInformation = new ProductInformation(
//                "red or green","Russia","", product);
//        em.persist(productInformation);
//
//        Product product1 = new Product("banana", 52.1);
//        em.persist(product1);
//        ProductInformation productInformation1 = new ProductInformation(
//                "yellow","Nigeria","", product1);
//        em.persist(productInformation1);
//
//
//        Product product2 = new Product("pear", 44.9);
//        em.persist(product2);
//        ProductInformation productInformation2 = new ProductInformation(
//                "red and green","Russia","", product2);
//        em.persist(productInformation2);
//
//        Product product3 = new Product("orange", 48.8);
//        em.persist(product3);
//        ProductInformation productInformation3 = new ProductInformation(
//                "orange","Italy","", product3);
//        em.persist(productInformation3);


        em.getTransaction().commit();

        em.close();
    }
}