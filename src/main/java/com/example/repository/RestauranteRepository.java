package com.example.repository;

import com.example.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
}