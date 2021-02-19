package geek.service;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductRepr> findAll();

    List<ProductRepr> findByTitle(String TitleFilter);

//    List<ProductRepr> findByPrice(String priceFilter);

    Optional<ProductRepr> findById(long id);

    void save(ProductRepr user);

    void delete(long id);
}