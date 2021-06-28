package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.validation.Valid;
import java.util.List;
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
    public String listPage(Model model) {
        logger.info("Product list page requested");
        model.addAttribute("products", productRepository.findAll());
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
        logger.info("Product editing page requeted");
        model.addAttribute("product", productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found")));
        return "product_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        logger.info("Delete product requested");
        if(productRepository.delete(id) != null) {
            return "redirect:/product";
        }
        throw new NotFoundException("Product not found");
    }

    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView modelAndView = new ModelAndView("not_found");
        modelAndView.addObject("message", ex.getMessage());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }
}
