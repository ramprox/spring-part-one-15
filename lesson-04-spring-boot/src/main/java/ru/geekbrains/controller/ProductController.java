package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("productNameFilter") Optional<String> productNameFilter,
                           @RequestParam("minCostFilter") Optional<BigDecimal> minCostFilter,
                           @RequestParam("maxCostFilter") Optional<BigDecimal> maxCostFilter,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sortBy") Optional<String> sortBy) {
        logger.info("Product list page requested");
        Specification<Product> spec = Specification.where(null);
        if(productNameFilter.isPresent() && !productNameFilter.get().isBlank()) {
            spec = spec.and(ProductSpecifications.productNamePrefix(productNameFilter.get()));
        }
        if(minCostFilter.isPresent()) {
            spec = spec.and(ProductSpecifications.minCost(minCostFilter.get()));
        }
        if(maxCostFilter.isPresent()) {
            spec = spec.and(ProductSpecifications.maxCost(maxCostFilter.get()));
        }
        Sort sortedBy;
        if(sortBy.isPresent() && !sortBy.get().isBlank()) {
            sortedBy = Sort.by(sortBy.get());
        } else {
            sortedBy = Sort.by("id");
        }
        model.addAttribute("products", productRepository.findAll(spec,
                PageRequest.of(page.orElse(1) - 1, size.orElse(10), sortedBy)));
        return "products";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        logger.info("New product page requested");
        model.addAttribute("product", new Product());
        return "product_form";
    }

    @PostMapping
    public String update(@Valid Product product, BindingResult result) {
        logger.info("Saving product");
        if(result.hasErrors()) {
            return "product_form";
        }
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        logger.info("Product editing page requested");
        model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        logger.info("Delete product requested");
        productRepository.deleteById(id);
        return "redirect:/product";
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
