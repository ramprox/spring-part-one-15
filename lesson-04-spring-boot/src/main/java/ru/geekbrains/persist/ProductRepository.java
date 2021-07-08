package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameStartsWith(String prefix);
    List<Product> findByCostGreaterThanEqual(BigDecimal minCost);
    List<Product> findByCostLessThanEqual(BigDecimal maxCost);
    List<Product> findByNameStartsWithAndCostGreaterThanEqual(String prefix, BigDecimal minCost);
    List<Product> findByNameStartsWithAndCostLessThanEqual(String prefix, BigDecimal maxCost);
    List<Product> findByCostBetween(BigDecimal minCost, BigDecimal maxCost);
    List<Product> findByNameStartsWithAndCostBetween(String prefix, BigDecimal minCost, BigDecimal maxCost);
}
