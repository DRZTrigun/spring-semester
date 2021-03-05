package geek.persist.store;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public final class ProductSpecification {

    public static Specification<Product> titleLike(String titleProduct) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + titleProduct + "%");
    }

    public static Specification<Product> minPrice(BigDecimal minPrice){
        return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    public static Specification<Product> maxPrice(BigDecimal maxPrice){
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
}
