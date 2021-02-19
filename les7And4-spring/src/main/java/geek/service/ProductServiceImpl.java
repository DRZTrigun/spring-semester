package geek.service;

import geek.store.Product;
import geek.store.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ProductRepr> findByTitle(String TitleFilter) {
        return productRepository.findProductByTitle(TitleFilter).stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<ProductRepr> findByPrice(String priceFilter) {
//        return productRepository.findProductsByPrice(priceFilter).stream()
//                .map(ProductRepr::new)
//                .collect(Collectors.toList());
//    }


    @Transactional
    @Override
    public Optional<ProductRepr> findById(long id) {
        return productRepository.findById(id)
                .map(ProductRepr::new);
    }

    @Transactional
    @Override
    public void save(ProductRepr product) { productRepository.save(new Product(product)); }

    @Override
    public void delete(long id) { productRepository.deleteById(id); }
}
