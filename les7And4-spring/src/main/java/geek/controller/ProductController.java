package geek.controller;

import geek.service.ProductRepr;
import geek.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;


    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public String ListPage(Model model, @RequestParam(name = "titleFilter") Optional<String> titleFilter,
                           @RequestParam("priceMinFilter")Optional<BigDecimal> priceMinFilter,
                           @RequestParam("priceMaxFilter")Optional<BigDecimal> priceMaxFilter,
                           @RequestParam("page")Optional<Integer> page,
                           @RequestParam("size")Optional<Integer> size,
                           @RequestParam("sortField")Optional<String> sortField
    ){
        logger.info("List page requested");

        Page<ProductRepr> products = productService.findByFilter(
                titleFilter.orElse(null),
                priceMinFilter.orElse(null),
                priceMaxFilter.orElse(null),
                page.orElse(1) - 1,
                size.orElse(4),
                sortField.orElse(null)
        );
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model){
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("product", productService.findById(id)
                .orElseThrow(NotFoundException::new));
        return "product_form";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("product") ProductRepr product, BindingResult result, Model model){
        logger.info("Update or create endpoint requested");

        // проверка на валидность(должно быть заполнено имя товара, описание и указана цена(должно быть строго больше 0)
        if (result.hasErrors()){
            return "product_form";
        }

        logger.info("Updating product with id {}", product.getId());
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String create(Model model) {
        logger.info("Creating new product request");

        model.addAttribute("product", new ProductRepr());
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id){
        logger.info("remove product with id{}", id);

        productService.delete(id);
        return "redirect:/product";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex){
        ModelAndView mav = new ModelAndView("not_found");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return  mav;
    }
}
