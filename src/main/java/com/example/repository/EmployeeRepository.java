package com.example.repository;

import com.example.model.Employee;
import com.example.model.Enum.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDni(String dni);

    List<Employee> findByAge(Integer age);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByRestauranteName(String SmashBurguerMadrid);

    List<Employee> findByFirstName(String name);

    List<Employee> findByRestaurante_FoodType(FoodType foodType);

    List<Employee> findByRestauranteAveragePrice(Double averagePrice);


    List<Employee> findByAgeGreaterThanEqual(Integer age);

    // Este metodo no está filtrando, solo está ordenando
    @Query("select e from Employee e order by e.firstName")
    List<Employee> findByOrderByFirstNameAsc();
    // OTRAS FORMAS DE ORDENAR TÍPICAS SERÍAN ORDENAR POR PRECIO ASC EN PRODUCTOS


    /*
    Si tuviera fecha de inicio en la que empezó a trabajar podemos calcular su antiguedad
        @Query(value = "select (current_date - e.startDate) from Employee e where e.nif = ?1")
    Duration findWorkDaysByNif(String nif);
     */
}
