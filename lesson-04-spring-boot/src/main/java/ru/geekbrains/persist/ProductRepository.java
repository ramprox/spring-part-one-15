package ru.geekbrains.persist;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByNameStartsWith(String prefix);
    List<Product> findByCostGreaterThanEqual(BigDecimal minCost);
    List<Product> findByCostLessThanEqual(BigDecimal maxCost);
    List<Product> findByNameStartsWithAndCostGreaterThanEqual(String prefix, BigDecimal minCost);
    List<Product> findByNameStartsWithAndCostLessThanEqual(String prefix, BigDecimal maxCost);
    List<Product> findByCostBetween(BigDecimal minCost, BigDecimal maxCost);
    List<Product> findByNameStartsWithAndCostBetween(String prefix, BigDecimal minCost, BigDecimal maxCost);
}
