package geek.persist;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "line_items")
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    @Column
    private BigDecimal price;

    @Column
    private Integer qty;

    @Column
    private String color;

    public LineItem() {
    }

    public LineItem(User user, Product product, BigDecimal price, Integer qty, String color) {
        this.user = user;
        this.product = product;
        this.price = price;
        this.qty = qty;
        this.color = color;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
