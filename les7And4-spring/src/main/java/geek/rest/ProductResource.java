package geek.rest;

import geek.controller.BadRequestException;
import geek.controller.NotFoundException;
import geek.service.ProductRepr;
import geek.service.ProductService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService){
        this.productService = productService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<ProductRepr> findAll(){
        return productService.findAll();
    }

    @GetMapping(path = "/{id}")
    public ProductRepr findById(@PathVariable("id") Long id){
        return productService.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    @GetMapping("filter")
    public Page<ProductRepr> listPage(
            @RequestParam("titleFilter")Optional<String> titleFilter,
            @RequestParam("priceMinFilter") Optional<BigDecimal> priceMinFilter,
            @RequestParam("priceMaxFilter") Optional<BigDecimal> priceMaxFilter,
            @Parameter(example = "1") @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("sortFiled") Optional<String> sortField ){
        return productService.findByFilter(
                titleFilter.orElse(null),
                priceMinFilter.orElse(null),
                priceMaxFilter.orElse(null),
                page.orElse(1) - 1,
                size.orElse(4),
                sortField.orElse(null));
    }

    @PostMapping(consumes = "application/json")
    public ProductRepr create(@RequestBody ProductRepr productRepr){
        if (productRepr.getId() != null){
            throw new BadRequestException();
        }
        productService.save(productRepr);
        return productRepr;
    }

    @PutMapping(consumes = "application/json")
    public void update(@RequestBody ProductRepr productRepr){
        if (productRepr.getId() == null){
            throw new BadRequestException();
        }
        productService.save(productRepr);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        productService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundException(NotFoundException ex){
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> badRequestException(BadRequestException ex){
        return new ResponseEntity<>("Entity bad request", HttpStatus.BAD_REQUEST);
    }
}
