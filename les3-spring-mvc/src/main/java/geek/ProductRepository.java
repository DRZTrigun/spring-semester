package geek;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    // Identity Map
    private Map<Long, Product> productMap = new ConcurrentHashMap<>();

    private AtomicLong identityProduct = new AtomicLong(0);

    @PostConstruct
    public void init(){
        this.insert(new Product("banana",
                "yellow fruit when green is not ripe",
                46.20));
        this.insert(new Product("apple",
                "red or green fruit, can be sweet or sour sweet",
                35.55));
        this.insert(new Product("pear",
                "very sweet, because contain a lot of sugar. Fruits are oblong or slightly rounded",
                44.90));
    }

    public void insert(Product product){
        long id = identityProduct.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    public void update (Product product){
        productMap.put(product.getId(), product);
    }

    public void delete(long id){
        productMap.remove(id);
    }

    public List<Product> findAll(){
        return new ArrayList<>(productMap.values());
    }

    public Product findById(long id){
        return productMap.get(id);
    }
}
