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

    @Builder.Default
    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne
    private Restaurante restaurante;

    //private User user; // Si quieres asociar la reseña a un usuario específico



}
