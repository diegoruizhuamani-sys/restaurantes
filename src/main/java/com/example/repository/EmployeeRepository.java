package com.example.repository;

import com.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByDni(String dni);

    List<Employee> findByAge(Integer age);

    List<Employee> findByLastName(String lastName);

    List<Employee> findByRestauranteName(String SmashBurguerMadrid);
}
