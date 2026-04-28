package com.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private Integer rating;

    @Builder.Default // para que builder no ponga este campo null
    private LocalDateTime creationDate = LocalDateTime.now();

    @ToString.Exclude// To String para excluir asociaciones
    @ManyToOne
    private Restaurante restaurante;

    @ToString.Exclude
    @ManyToOne
    private Dish dish;

    //private User user; // Si quieres asociar la reseña a un usuario específico



}
