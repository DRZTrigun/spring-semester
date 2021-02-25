package geek.service;

import geek.store.Product;
import geek.store.ProductRepository;
import geek.store.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) { this.productRepository = productRepository; }

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductRepr> findByFilter (String titleFilter, BigDecimal minPrice, BigDecimal maxPrice,
                                           Integer page, Integer size, String sortField) {

        Specification<Product> spec = Specification.where(null);

        if (titleFilter != null && !titleFilter.isBlank()) {
            spec = spec.and(ProductSpecification.titleLike(titleFilter));
        }

        if (minPrice != null){
            spec = spec.and(ProductSpecification.minPrice(minPrice));
        }

        if (maxPrice != null){
            spec = spec.and(ProductSpecification.maxPrice(maxPrice));
        }

        if (sortField != null && !sortField.isBlank()) {
            return productRepository.findAll(spec, PageRequest.of(page, size, Sort.by(sortField)))
                    .map(ProductRepr::new);
        }

        return productRepository.findAll(spec, PageRequest.of(page, size))
                .map(ProductRepr::new);
    }

    @Transactional
    @Override
    public Optional<ProductRepr> findById(long id) {
        return productRepository.findById(id)
                .map(ProductRepr::new);
    }

    @Transactional
    @Override
    public void save(ProductRepr product) {
        Product productToSave = new Product(product);
        productRepository.save(productToSave);
        if (product.getId() == null) {
            product.setId(productToSave.getId());
        }
    }

    @Override
    public void delete(long id) { productRepository.deleteById(id); }
}
