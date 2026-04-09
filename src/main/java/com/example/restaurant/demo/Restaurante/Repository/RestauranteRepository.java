package com.example.restaurant.demo.Restaurante.Repository;

import com.example.restaurant.demo.Restaurante.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}