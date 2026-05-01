package com.example.model;

import com.example.model.Enum.FoodType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
@Setter
@Getter
@Entity
@Table(name = "Restaurantes")
public class Restaurante {


    //El this sirve para poner valores y se guarden.
    //Estos métodos sirven para  acceder y poder cambiar valores de un objeto(Getter(permite obtener un atributo) y Setter(permite cambiar un valor))
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    //@Column(unique = true)
    private String name;

    private Double averagePrice;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active = true;

    private Integer numberEmployees;

    //Fecha de fundación
    @CreationTimestamp    //    para crear fechas por defecto
    private LocalDate startDate = LocalDate.now(); // =LocalDate.now(); // valor por defecto a la fecha actual

    //tipo de comida
    @Enumerated(EnumType.STRING)
    private FoodType foodType= FoodType.SPANISH; // valor por defecto a SPANISH


//Metodo constructor para crear Restaurantes con valores

    //Parametros o Atributos

    @ManyToOne
    @JoinColumn
    private Employee employee;

    public Restaurante(String name, Double averagePrice, Integer numberEmployees) {
        this.name = name;
        this.averagePrice = averagePrice;        //
        this.numberEmployees = numberEmployees;
    }

    public Restaurante() {

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

}

