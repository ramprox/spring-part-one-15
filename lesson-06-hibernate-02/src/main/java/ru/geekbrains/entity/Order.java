package ru.geekbrains.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "datetime")
    private LocalDateTime localDateTime;

    public Order() {
    }

    public Order(Long id, Customer customer, Product product) {
        this.id = id;
        this.customer = customer;
        this.product = product;
    }

    public Order(Long id, Customer customer, Product product, BigDecimal cost, LocalDateTime localDateTime) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.cost = cost;
        this.localDateTime = localDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return String.format("Order: { id = %d, customer = %s, product = %s, cost = %.2f, " +
                        "date_time = %5$td-%5$tm-%5$tY %5$tH:%5$tM:%5$tS }",
                id, customer.getName(), product.getName(), cost, localDateTime);
    }
}
