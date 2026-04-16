package com.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Lineas_pedido")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    private Integer quantity;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Dish dish;

    public OrderLine() {} //Constructor vacío

    public OrderLine(Integer quantity, Order order, Dish dish) {//Constructor generado autm.
        this.quantity = quantity;
        this.order = order;
        this.dish = dish;
    }
//Getter and Setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
