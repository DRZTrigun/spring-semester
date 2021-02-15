package geek.store;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "shoppers")
@NamedQueries({
        @NamedQuery(name = "shopperByName", query = "from Shopper s where s.shoppername=:shoppername"),
        @NamedQuery(name = "allShoppers", query = "from Shopper")
})
public class Shopper {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, unique = true, nullable = false)
    private String shoppername;

    @Column(length = 512, nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "products_shoppers",
            joinColumns = @JoinColumn(name = "shoppers_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id")
    )
    private List<Product> products;

    public Shopper() {
    }

    public Shopper(String shoppername, String password) {
        this.shoppername = shoppername;
        this.password = password;
    }

    public Shopper(String shoppername) {
        this.shoppername = shoppername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShoppername() {
        return shoppername;
    }

    public void setShoppername(String shoppername) {
        this.shoppername = shoppername;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Сustomer{" +
                "id=" + id +
                ", username='" + shoppername + '\'' +
                ", password='" + password + '\'' +
                ", products=" + products +
                '}';
    }
}
