package geek.service;

import geek.store.Product;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

//DTO
public class ProductRepr {

    private Long id;

    @NotEmpty
    private String title;

    private String descriptionproduct;

    @NotNull
    @Positive
    private BigDecimal price;

    public ProductRepr(){
    }

    public ProductRepr(String title){
        this.title = title;
    }

    public ProductRepr(Product product) {
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

    public void setTitle(String title) {
        this.title = title;
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

}
