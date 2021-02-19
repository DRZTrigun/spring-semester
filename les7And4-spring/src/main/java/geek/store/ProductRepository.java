package geek.store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    List<Product> findProductByTitle(String title);

    @Query("select p from Product p where p.title like concat('%',:title,'%') ")
    List<Product> findProductByTitle(@Param("title") String title);

//    @Query("select p from Product p order by p.price ASC ")
//    List<Product> findProductsByPrice (@Param("title") String title);
}
