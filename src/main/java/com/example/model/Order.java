package com.example.model;

import com.example.model.Enum.OrderStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pedidos")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private LocalDateTime date = LocalDateTime.now(); // valor por defecto a la fecha y hora actual

    private Double totalPrice;

    private Double tip;

    private Integer numPeople;


    private Integer tableNumber;
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;
    @ManyToOne
    private Restaurante restaurant;

    public Order() {
    }

    public Order(Double tip, Integer tableNumber, Integer numPeople, Restaurante restaurant) {
        this.tip = tip;
        this.tableNumber = tableNumber;
        this.numPeople = numPeople;
        this.restaurant = restaurant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTip() {
        return tip;
    }

    public void setTip(Double tip) {
        this.tip = tip;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Integer getNumPeople() {
        return numPeople;
    }

    public void setNumPeople(Integer numPeople) {
        this.numPeople = numPeople;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Restaurante getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurante restaurant) {
        this.restaurant = restaurant;
    }

    private void restaurante() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", totalPrice=" + totalPrice +
                ", tip=" + tip +
                ", tableNumber=" + tableNumber +
                ", numPeople=" + numPeople +
                ", status=" + status +
                '}';
    }

}
