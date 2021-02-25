package geek.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll();

    Page<ProductRepr> findByFilter(String titleFilter, BigDecimal minPrice, BigDecimal maxPrice,
                                   Integer page, Integer size, String sortField);

    Optional<ProductRepr> findById(long id);

    void save(ProductRepr user);

    void delete(long id);
}