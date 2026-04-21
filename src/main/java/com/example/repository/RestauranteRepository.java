package com.example.repository;

import com.example.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
    //    @Transactional
//    @Modifying
//    @Query("delete from Restaurant r where r.name = ?1")
//    void deleteByNameTodoGuay(String name);
}