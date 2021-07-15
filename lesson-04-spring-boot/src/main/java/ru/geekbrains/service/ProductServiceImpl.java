package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.controller.ProductListParams;
import ru.geekbrains.controller.ProductSpecifications;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> findWithFilter(ProductListParams listParams) {
        Specification<Product> spec = Specification.where(null);
        if(listParams.getProductNameFilter() != null && !listParams.getProductNameFilter().isBlank()) {
            spec = spec.and(ProductSpecifications.productNamePrefix(listParams.getProductNameFilter()));
        }
        if(listParams.getMinCostFilter() != null) {
            spec = spec.and(ProductSpecifications.minCost(listParams.getMinCostFilter()));
        }
        if(listParams.getMaxCostFilter() != null) {
            spec = spec.and(ProductSpecifications.maxCost(listParams.getMaxCostFilter()));
        }
        Sort sortedBy;
        if(listParams.getSortBy() != null && !listParams.getSortBy().isBlank()) {
            sortedBy = Sort.by(listParams.getSortBy());
        } else {
            sortedBy = Sort.by("id");
        }
        if(listParams.getDirection() != null && !listParams.getDirection().isBlank() && listParams.getDirection().equals("desc")) {
            sortedBy = sortedBy.descending();
        } else {
            sortedBy = sortedBy.ascending();
        }
        return productRepository.findAll(spec,
                PageRequest.of(Optional.ofNullable(listParams.getPage()).orElse(1) - 1,
                        Optional.ofNullable(listParams.getSize()).orElse(10), sortedBy));
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

}
