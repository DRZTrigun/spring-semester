package geek.store;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public final class ProductSpecification {

    public static Specification<Product> titleLike(String titleProduct) {
        return ((root, query, cb) -> cb.like(root.get("title"), "%" + titleProduct + "%"));
    }

    public static Specification<Product> minPrice(BigDecimal minPrice){
        return ((root, query, cb) -> cb.ge(root.get("price"), minPrice));
    }

    public static Specification<Product> maxPrice(BigDecimal maxPrice){
        return ((root, query, cb) -> cb.le(root.get("price"), maxPrice));
    }
}
