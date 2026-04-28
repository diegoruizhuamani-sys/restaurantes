package com.example.repository;

import com.example.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    //Pensar que atributos tenemos para filtrar nuestras variables
    //Circulito Rojo/Find collection/Query condition para           . Order Atributte para ordenar.

    //Filtro para ver restarantes en orden de creación descendente
    List<Review> findByRestaurante_IdOrderByCreationDateDesc(Long id);
    List<Review> findByDish_IdOrderByCreationDateDesc(Long id);

    List<Review> findByRatingAndRestaurante_IdOrderByCreationDateDesc(Integer rating, Long id);




    //Filtrar reviews con las mejores puntuaciones.

    //Filtrar restaurantes con 4 estrellas o más
}