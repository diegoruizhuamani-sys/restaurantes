package com.example.repository;

import com.example.model.Employee;
import com.example.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
