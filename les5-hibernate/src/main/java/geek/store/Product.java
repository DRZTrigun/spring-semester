package geek.store;

import javax.persistence.*;

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

    private String descriptionproduct;

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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productname='" + productname + '\'' +
                ", price=" + price +
                ", descriptionproduct='" + descriptionproduct + '\'' +
                '}';
    }
}
