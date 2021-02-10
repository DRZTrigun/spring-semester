package geek.controller;

import geek.Product;
import geek.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping
    public String ListPage(Model model){
        logger.info("List page requested");

        model.addAttribute("products", productRepository.findAll());
        return "product";
    }

    @GetMapping("/{id}")
    public String EditPage(@PathVariable("id") Long id, Model model){
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("product", productRepository.findById(id));
        return "prodcut_form";
    }

    @PostMapping("/update")
    public String update(@Valid Product product, BindingResult result){
        logger.info("Update or create endpoint requested");

        // проверка на валидность(должно быть заполнено имя товара, описание и указана цена(должно быть строго больше 0)
        // возможно лучше всего цену сделать тоже типа String
        // и писать "нет товара" когда товар закончился тем самым не указывая цену товара
        if (result.hasErrors()){
            return "prodcut_form";
        }

        if (product.getId() != null){
            logger.info("Updating product with id {}", product.getId());
            productRepository.update(product);
        } else {
            logger.info("Creating new product");
            productRepository.insert(product);
        }
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String create(Model model) {
        logger.info("Creating new product request");

        model.addAttribute("product", new Product());
        return  "prodcut_form";
    }


    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id){
        logger.info("remove product with id{}", id);

        productRepository.delete(id);
        return "redirect:/product";
    }
}
