package ru.geekbrains.persist;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

public class Product {

    private Long id;

    @NotBlank
    private String name;

    @PositiveOrZero
    private float cost;

    public Product() {
    }

    public Product(Long id, String name, float cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
