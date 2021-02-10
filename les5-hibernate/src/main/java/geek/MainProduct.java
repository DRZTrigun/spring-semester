package geek;

import geek.store.Product;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MainProduct {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernateproduct.cfg.xml")
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        //INSERT
//        em.getTransaction().begin();
//
//        Product product = new Product("banana",
//                "yellow",
//                49.90);
//        Product product1 = new Product("apple",
//                "red or green fruit, can be sweet or sour sweet",
//                35.55);
//        Product product2 = new Product("pear",
//                "very sweet, because contain a lot of sugar. Fruits are oblong or slightly rounded",
//                44.90);
//        Product product3 = new Product("orange",
//                "the fruit of a tropical citrus tree, distinguished by its bright color and rich taste",
//                48.80);
//        em.persist(product);
//        em.persist(product1);
//        em.persist(product2);
//        em.persist(product3);
//
//        em.getTransaction().commit();
//        em.close();
//
//        // SELECT
//        System.out.println("Product with id 1");
//        Product product = em.find(Product.class, 1L);
//        System.out.println(product);
//
//        // HQL, JPQL
//        System.out.println("All Products in table");
//        List<Product> productList = em.createQuery("from Product", Product.class)
//                .getResultList();
//        System.out.println(productList);
//
//        System.out.println("Product with name 'apple'");
//        Object apple = em.createQuery("from Product p where p.productname = :productname")
//                .setParameter("productname", "apple")
//                .getSingleResult();
//        System.out.println(apple);
//
//        // SQL
//        productList = em.createNativeQuery("select * from products", Product.class)
//                .getResultList();
//        System.out.println(productList);
//
//        em.createNamedQuery("productByName")
//                .setParameter("productname", "apple")
//                .getSingleResult();
//        em.close();
//
//        // UPDATE
//
//        Product product = em.find(Product.class, 1L);
//
//        em.getTransaction().begin();
//
//        product.setPrice(52.10);
//
//        em.getTransaction().commit();
//
//        List<Product> productList = em.createQuery("from Product", Product.class)
//                .getResultList();
//        System.out.println(productList);
//
//        em.close();
//
//        DELETE
//
//        em.getTransaction().begin();
//
//        Product product = em.find(Product.class, 3L);
//        if (product != null){
//            em.remove(product);
//        }
//
//        em.createQuery("delete  from Product where productname=:productname")
//                .setParameter("productname", "orange")
//                .executeUpdate();
//
//        em.getTransaction().commit();
//
//        List<Product> productList = em.createQuery("from Product", Product.class)
//                .getResultList();
//        System.out.println(productList);
//
//        em.close();
    }
}
