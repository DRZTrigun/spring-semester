package geek.basketService;

import geek.basket.basketUser;

import java.math.BigDecimal;

public class basketRepr {

    private Long id;

    private String titleProduct;

    private BigDecimal priceProduct;

    public basketRepr() {
    }

    public basketRepr(basketUser basketUser){
        this.id = basketUser.getId();
        this.titleProduct = basketUser.getTitleProduct();
        this.priceProduct = basketUser.getPriceProduct();
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
}
