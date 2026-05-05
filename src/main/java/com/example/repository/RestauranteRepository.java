package com.example.repository;

import com.example.model.Enum.FoodType;
import com.example.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
//    @Transactional
//    @Modifying
//    @Query("delete from Restaurant r where r.name = ?1")
//    void deleteByNameTodoGuay(String name);

    List<Restaurante> findByActiveTrue();
    Optional<Restaurante> findByIdAndActiveTrue(Long id);


    // Query con filtros:
    @Query("""
        SELECT r from Restaurante r
        WHERE r.active = true
        AND (:foodType IS NULL OR r.foodType = :foodType)
                AND (:price IS NULL OR r.averagePrice <= :price)
        """)
    List<Restaurante> findActiveFiltering(@Param("foodType") FoodType foodType, @Param("price") Double price);

    // Filtro por nombre

    // Filtro por foodType

    // Filtro por precio

    // Filtro por código postal

    // Si se vuelven muy complejos los filtros se puede usar:
    // API Specification
}