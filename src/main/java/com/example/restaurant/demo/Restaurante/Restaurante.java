package com.example.restaurant.demo.Restaurante;

import jakarta.persistence.*;

@Entity
@Table(name = "Restaurantes")
    public class Restaurante{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long id;
        @Column(unique = true)
        private String name;

        private Double averagePrice;
        @Column(columnDefinition = "BOOLEAN DEFAULT true")
        private Boolean active= true;

        private Integer numberEmployees;


    }

