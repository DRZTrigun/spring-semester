package geek.basket;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "basket_users")
public class basketUser {

    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private String titleProduct;

    @Column
    private BigDecimal priceProduct;

    public basketUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleProduct() {
        return titleProduct;
    }

    public void setTitleProduct(String titleProduct) {
        this.titleProduct = titleProduct;
    }

    public BigDecimal getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(BigDecimal priceProduct) {
        this.priceProduct = priceProduct;
    }

    @Override
    public String toString() {
        return "basketUser{" +
                "id=" + id +
                ", title='" + titleProduct + '\'' +
                ", price=" + priceProduct +
                '}';
    }
}
