package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.controller.NotFoundException;
import ru.geekbrains.controller.ProductListParams;
import ru.geekbrains.persist.Product;
import ru.geekbrains.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {
    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(produces = "application/json")
    public Page<Product> findWithFilter(ProductListParams params) {
        return productService.findWithFilter(params);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Product findById(@PathVariable("id") Long id) {
        return productService.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));
    }

    @PostMapping(produces = "application/json")
    public Product create(@RequestBody Product product) {
        if(product.getId() != -1) {
            throw new BadRequestException("Product Id must be null");
        }
        productService.save(product);
        return product;
    }

    @PutMapping(produces = "application/json")
    public void update(@RequestBody Product product) {
        if(product.getId() == -1) {
            throw new BadRequestException("Product Id mustn't be null");
        }
        productService.save(product);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public void delete(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }
}
