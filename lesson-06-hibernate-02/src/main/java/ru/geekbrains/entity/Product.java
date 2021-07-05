package ru.geekbrains.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "cost")
    private BigDecimal cost;

    @OneToMany(mappedBy = "customer")
    private List<Order> customers;

    public Product() {
    }

    public Product(Long id, String name, BigDecimal cost, List<Order> customers) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.customers = customers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public List<Order> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Order> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return String.format("Product: { id = %d, name = %s, cost = %.2f }", id, name, cost);
    }
}
