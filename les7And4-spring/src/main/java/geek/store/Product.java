package geek.store;

import geek.service.ProductRepr;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private String descriptionproduct;

    @Column(nullable = false)
    private BigDecimal price;

    public Product(){
    }

    public Product(String title){
        this.title = title;
    }

    public Product(ProductRepr product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.descriptionproduct = product.getDescriptionproduct();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String productname) {
        this.title = productname;
    }

    public String getDescriptionproduct() {
        return descriptionproduct;
    }

    public void setDescriptionproduct(String descriptionproduct) {
        this.descriptionproduct = descriptionproduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", descriptionproduct='" + descriptionproduct + '\'' +
                ", price=" + price +
                '}';
    }
}
