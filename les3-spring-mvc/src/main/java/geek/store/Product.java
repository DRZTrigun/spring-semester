package geek.store;

public class Product {

    private Long id;

    private String productname;

    private String descriptionproduct;

    private Double price;

    public Long getId() {
        return id;
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
