package geek.basketService;

import geek.service.UserRepr;

import java.util.Optional;

public interface BasketService {

    Optional<basketRepr> putInBasket(long id);

    void deleteFromBasket(long id);

    void changeBasket();

}
