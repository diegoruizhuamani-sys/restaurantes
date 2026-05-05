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


    // Métodos Getter y Setter
    // Get obtener, permite obtener un atributo
    // Set cambiar, permite cambiar un valor
    //El this sirve para poner valores y se guarden.
    //Estos métodos sirven para  acceder y poder cambiar valores de un objeto(Getter(permite obtener un atributo) y Setter(permite cambiar un valor))
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private Double averagePrice;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean active = true;

    private Integer numberEmployees;

    // fecha de fundación

    //@CreationTimestamp // esta anotación es para que la base de datos genere la fecha, util para registrar fecha
    // automaticamente sin preocuparse de tener que cambiarla
    private LocalDate startDate = LocalDate.now(); //  // valor por defecto a la fecha actual

    // private LocalDateTime ultimaReservan = LocalDateTime.now();

    // tipo de comida
    @Enumerated(EnumType.STRING)
    private FoodType foodType;


    // bidireccional
//    @OneToMany
//    List<Employee> employees = new ArrayList<Employee>();

    // metodo constructor para crear Restaurantes con valores

    public Restaurante(String name, Double averagePrice, Integer numberEmployees) {
        this.name = name;
        this.averagePrice = averagePrice;
        this.numberEmployees = numberEmployees;
    }

    // metodo constructor vacío
    public Restaurante() {
    }

    // Metodo toString para ver los datos de un restaurante al imprimirlo


    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", averagePrice=" + averagePrice +
                ", active=" + active +
                ", numberEmployees=" + numberEmployees +
                ", startDate=" + startDate +
                ", foodType=" + foodType +
                '}';
    }

    // Próximas tareas:
    // Enum
    // Fecha
    // Asociación con otra entidad/tabla

}