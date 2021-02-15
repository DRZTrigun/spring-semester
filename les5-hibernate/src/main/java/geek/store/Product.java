package geek.store;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "productByName", query = "from Product p where p.productname=:productname"),
        @NamedQuery(name = "allProducts", query = "from Product")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, unique = true, nullable = false)
    private String productname;

    @Column(nullable = false)
    private Double price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "products_shoppers",
            joinColumns = @JoinColumn(name = "products_id"),
            inverseJoinColumns = @JoinColumn(name = "shoppers_id")
    )
    private List<Shopper> shoppers;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductInformation> productInformations;

    public Product(){
    }

    public Product(String productname, Double price) {
        this.productname = productname;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Shopper> getShoppers() {
        return shoppers;
    }

    public void setShoppers(List<Shopper> shoppers) {
        this.shoppers = shoppers;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productname='" + productname + '\'' +
                ", price=" + price +
                ", descriptionproduct='" + '\'' +
                '}';
    }
}
