package geek.store;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.net.Socket;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    @Query("select p from Product p " +
            "where (p.title like concat('%',:title,'%') or :title is null) and " +
            "(p.price >= :minPrice or :minPrice is null) and " +
            "(p.price <= :maxPrice or :maxPrice is null)")
    List<Product> findWithFilter(@Param("title") String titleFilter,
                                 @Param("minPrice") BigDecimal minPrice,
                                 @Param("maxPrice") BigDecimal maxPrice);

}
