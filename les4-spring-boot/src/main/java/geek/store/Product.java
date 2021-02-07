package geek.store;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Product {

    private Long id;

    @NotEmpty
    private String productname;

    @NotEmpty
    private String descriptionproduct;

    @NotNull
    @Positive
    private Double price;

    public Long getId() {
        return id;
    }

    public Product(){
    }


    public Product(String productname, String descriptionproduct, Double price) {
        this.productname = productname;
        this.descriptionproduct = descriptionproduct;
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDescriptionproduct() {
        return descriptionproduct;
    }

    public void setDescriptionproduct(String descriptionproduct) {
        this.descriptionproduct = descriptionproduct;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
