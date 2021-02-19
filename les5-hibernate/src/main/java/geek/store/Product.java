package geek.store;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "productByName", query = "from Product p where p.title=:title"),
        @NamedQuery(name = "allProducts", query = "from Product")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private BigDecimal price;

    @OneToMany(mappedBy = "product")
    private List<LineItemStore> lineItem;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductInformation> productInformations;

    public Product(){
    }

    public Product(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<LineItemStore> getLineItem() {
        return lineItem;
    }

    public void setLineItem(List<LineItemStore> lineItem) {
        this.lineItem = lineItem;
    }

    public List<ProductInformation> getProductInformations() {
        return productInformations;
    }

    public void setProductInformations(List<ProductInformation> productInformations) {
        this.productInformations = productInformations;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productname='" + title + '\'' +
                ", price=" + price +
                ", descriptionproduct='" + '\'' +
                '}';
    }
}
