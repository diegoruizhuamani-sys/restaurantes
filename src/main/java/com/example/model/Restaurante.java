package com.example.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

import java.util.Date;

@Entity
@Table(name = "Restaurantes")
public class Restaurante {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(unique = true)
    private String name;

    private Double averagePrice;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active = true;

    private Integer numberEmployees;

    //Fecha de fundación
    @CreationTimestamp
    private LocalDate startDate = LocalDate.now(); // =LocalDate.now(); // valor por defecto a la fecha actual

    //tipo de comida
    @Enumerated(EnumType.STRING)
    private FoodType foodType= FoodType.SPANISH; // valor por defecto a SPANISH

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }


//Metodo constructor para crear Restaurantes con valores

    //Parametros o Atributos
    public Restaurante(String name, Double averagePrice, Integer numberEmployees) {
        this.name = name;
        this.averagePrice = averagePrice;        //
        this.numberEmployees = numberEmployees;
    }

    public Restaurante() {

    }

    //Estos métodos sirven para  acceder y poder cambiar valores de un objeto(Getter(permite obtener un atributo) y Setter(permite cambiar un valor))
    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;//El this sirve para poner valores y se guarden.
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Double getAveragePrice() {

        return averagePrice;
    }

    public void setAveragePrice(Double averagePrice) {

        this.averagePrice = averagePrice;
    }

    public Boolean getActive() {

        return active;
    }

    public void setActive(Boolean active) {

        this.active = active;
    }

    public Integer getNumberEmployees() {

        return numberEmployees;
    }

    public void setNumberEmployees(Integer numberEmployees) {
        this.numberEmployees = numberEmployees;


    }

    @Override
    public String toString() {
        return "Restaurante{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averagePrice=" + averagePrice +
                ", active=" + active +
                ", numberEmployees=" + numberEmployees +
                ", startDate=" + startDate +
                ", foodType=" + foodType +
                '}';
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}

