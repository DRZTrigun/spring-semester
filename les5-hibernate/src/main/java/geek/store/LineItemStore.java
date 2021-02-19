package geek.store;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "line_items")
public class LineItemStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shopper shopper;

    @ManyToOne
    private Product product;

    @Column
    private BigDecimal price;

    @Column
    private Integer qty;

    @Column
    private String color;

    public LineItemStore() {
    }

    public LineItemStore(Shopper shopper, Product product, BigDecimal price, Integer qty, String color) {
        this.shopper = shopper;
        this.product = product;
        this.price = price;
        this.qty = qty;
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shopper getShopper() {
        return shopper;
    }

    public void setShopper(Shopper shopper) {
        this.shopper = shopper;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
