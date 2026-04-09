package com.example;

import com.example.model.Employee;
import com.example.model.Restaurante;
import com.example.repository.EmployeeRepository;
import com.example.repository.RestauranteRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

//Una clase con @Entity equivale a una tabla en base de datos
//un objeto equivale a una fila en una tabla en base de datos
@SpringBootApplication
public class RestaurantApplication {

    public RestaurantApplication() {
    }

    public static void main(String[] args) {
        var context = SpringApplication.run(RestaurantApplication.class, args);

        //obtener los repositorios para poder hacer operaciones de base de datos con ellos
        RestauranteRepository restauranteRepository = context.getBean(RestauranteRepository.class);
        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);


        //crear un objeto restaurante: new
        Restaurante BarDiego = new Restaurante();
        BarDiego.setName("DiegoBar");//Cambiar de nombre o valor que le des a objeto con Getter y Setter
        BarDiego.setAveragePrice(20.5);
        BarDiego.setNumberEmployees(5);


        //Guardar el restaurante en base de datos usando el repositorio : .save()
        restauranteRepository.save(BarDiego);

        //Crear objetos con datos
        Restaurante rest2 = new Restaurante("100 Montaditos", 33.10, 7);
        restauranteRepository.save(rest2);
        //Crearlo vacio y actualizarlo con métodos set
        Restaurante rest3 = new Restaurante();
        rest3.setName("Rest3");
        rest3.setAveragePrice(50.22);
        rest3.setNumberEmployees(10);

        restauranteRepository.save(rest3);

        //Ejercicio Crear empleado
        Employee emp1 = new Employee("Diego", "Ruiz", 25, "03391967V");
        employeeRepository.save(emp1);

        Employee emp2 = new Employee();
        employeeRepository.save(emp2);

        System.out.println(emp2);
        System.out.println(emp1);

        // obtener todos los restaurantes de la base de datos
        // Select * from restaurantes de base de datos
        // SELECT * from restaurantes;
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        System.out.println(restaurantes);

        // .size() nos dice el numero de elementos en la lista de restaurantes
        for (int i = 0; i < restaurantes.size(); i++) {
            System.out.println(restaurantes.get(i));
        }
        for (Restaurante Restaurante : restaurantes) { // bucle foreach: itera uno a uno los restaurantes
            System.out.println(Restaurante);


        }

        List<Employee> Employees = employeeRepository.findAll();
        System.out.println(Employees);
        //imprimir los restaurantes obtenidos con un bucle for

        ;
        for (Employee empleado : Employees) {
            System.out.println(empleado);
        }
    }
}
