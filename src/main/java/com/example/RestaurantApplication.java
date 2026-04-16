package com.example;

import com.example.model.*;
import com.example.model.Enum.DishType;
import com.example.model.Enum.FoodType;
import com.example.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.*;

//Una clase con @Entity equivale a una tabla en base de datos
//un objeto equivale a una fila en una tabla en base de datos
@SpringBootApplication
public class RestaurantApplication {

    public RestaurantApplication() {
    }

    public static void main(String[] args) {
        var context = SpringApplication.run(RestaurantApplication.class, args);

        //Añadir/obtener los repositorios para poder hacer operaciones de base de datos con ellos
        RestauranteRepository restauranteRepository = context.getBean(RestauranteRepository.class);
        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
        DishRepository dishRepository = context.getBean(DishRepository.class);
        OrderRepository orderRepository = context.getBean(OrderRepository.class);
        OrderLineRepository orderLineRepository = context.getBean(OrderLineRepository.class);
//Nombre de interfaz en MAY.//Nombre de clase Minus.


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

        //saveAll
        Restaurante DiegoBar = new Restaurante("diegoBar", 10.0, 3);
        Restaurante Rest3 = new Restaurante("AngelBar", 15.0, 4);
        //opcion clasica para crear lista:

        List<Restaurante> sitiosParaComer = new ArrayList<>(); //crear una lista vacía
        List<String> alumnos = new ArrayList<>();
        List<Double> precios = new ArrayList<>();

        //opcion moderna para crear lista:
        List<Restaurante> sitiosGuaposParaComer = List.of(DiegoBar, Rest3); // crear una lista con elemntos dentro

        restauranteRepository.saveAll(sitiosGuaposParaComer);

        //count()Para contar cuantas filas hay en la tabla:
        long numeroRestaurantes = restauranteRepository.count();

        if (numeroRestaurantes>0) {
            System.out.println("Hay para comer, todos tranquis" );
            System.out.println("hay" + numeroRestaurantes + "restaurantes");
        } else{
            System.out.println("nos morimos de hambre");;
        }

        // existById devuelve boolean
        long id = 1;
        boolean existe = restauranteRepository.existsById(id);
        if (existe)
            System.out.println("restaurante 1 si existe");
        else
            System.out.println("restaurante 1 no existe");



        //restauranteRepository.existsById(2L); // Long ;


        // deleteAll borrar todas las filas de la tabla
        // restauranteRepository.deleteAll();

        //deleteById borrar una fila de la tabla por su id
         restauranteRepository.deleteById(3L);
         //restauranteRepository.deleteById(rest2.getId());

        //delete, borra pasando el objeto
        restauranteRepository.delete(DiegoBar);

        //findById traer un restaurante/empleado por su id
        Long idABuscar = 2L;
        // Restaurante restauranteFromDatabase = restauranteReposity.findById(idABuscar);
        Optional<Restaurante> restauranteFromDatabase = restauranteRepository.findById(idABuscar);
        // var restauranteFromDatabase = restauranteRepository.findById(idABuscar);
        if (restauranteFromDatabase.isPresent()) {
            Restaurante restaurante2 = restauranteFromDatabase.get();
            System.out.println(restaurante2);

        }

        //Resumen
        //findAll
        //findById
        //exitsById
        //count()

        //Crear un restaurante español
        Restaurante restSpain = new Restaurante();
        restSpain.setFoodType(FoodType.SPANISH);
        System.out.println(restSpain);
        restauranteRepository.save(restSpain);
        //Crear un restaurante  de comida japonesa
        Restaurante restJapan = new Restaurante();
        restJapan.setFoodType(FoodType.JAPANESE);
        System.out.println(restJapan);
        restauranteRepository.save((restJapan));
        //Probar a intentar otro tipo de comida y ver que no deja


        //Probar fecha de startDate del restaurante
        Restaurante smashBurguer = new Restaurante();
        smashBurguer.setName("Smash Burguer Madrid");
        smashBurguer.setStartDate(LocalDate.now()); //fecha actual
        restauranteRepository.save(smashBurguer);
        System.out.println(smashBurguer);

        //fecha futura
        Restaurante sidreria = new Restaurante();
        sidreria.setStartDate(LocalDate.of(2026, 6, 20));

        sidreria.setName("Sidreria Asturiana");
        restauranteRepository.save(sidreria);
        System.out.println(sidreria);
       // LocalDate solo da año mes y dia
        //LocalTime solo da hora minuto segundo
        // LocalDateTime da año mes dia hora minuto segundo

        //MANY TO ONE - ASOCIAR UN RESTAURANTE A DOS EMPLEADOS
        //Paso 1. crear restaurante y guardarlo
        Restaurante RestauranteEjemplo = new Restaurante("La Carcel", 25.0, 2);
        RestauranteEjemplo.setName("La Carcel");
        restauranteRepository.save(RestauranteEjemplo);

        // Paso 2. crear empleados, setRestaurant y guardar
        Employee empEjemplo1 = new Employee("Maria", "Gomez", 30, "12345678A");
        empEjemplo1.setRestaurante(RestauranteEjemplo);
        employeeRepository.save(empEjemplo1);
        System.out.println(empEjemplo1);

        Employee empEjemplo2 = new Employee("Diego", "Arturo", 23, "02292863H");
        empEjemplo2.setRestaurante(RestauranteEjemplo);
        employeeRepository.save(empEjemplo2);
        System.out.println(empEjemplo2);



        //Bucle for para iterar sobre todos los empleados imprimiendo el nombre del empleado y el nombre del restaurante
        // si lo tiene
//employeeRepository.findAll() devuelve lista de empleados
        //for (Employee trabajadores : trabajadores) {
        //System.out.println(trabajador,getFirstName() + "trabaja en" + trabajador.getRestaurante().getName);



        List<Employee> empleados = employeeRepository.findAll();
        for (Employee empleado : empleados) { //
            System.out.println(empleado.getFirstName() + " " + empleado.getLastName() );
            if (empleado.getRestaurante() != null) {//Si el trabajador.getRestaurante es distinto a null : SOUT
                System.out.println("Trabaja en el restaurante: " + empleado.getRestaurante().getName());
            } else {
                System.out.println("No tiene restaurante asignado");
            }

        }

         //probar a filtrar
        //List<Employee> empleados20 = employeeRepository.findByAge(20);
        List<Employee> empleadosBurguer = employeeRepository.findByRestauranteName("La Carcel");
        System.out.println("Empleados que trabajan en La carcel : " + empleadosBurguer);
        // filtrar por apellido
        List<Employee> empleadosApellido = employeeRepository.findByLastName("Arturo");
        System.out.println("Empleado que trabaja en la carcel con apellido Artruo: " + empleadosApellido);
        //filtrar por edad
        List<Employee> empleadosEdad = employeeRepository.findByAge(23);
        System.out.println("Empleado que trabaja en la carcel con 23 años " + empleadosEdad);


// CREAR PLATOS Y GUARDARLOS
        Restaurante restaurantSpain = new Restaurante();
        restauranteRepository.save(restaurantSpain);
        Dish plato1 = new Dish(null, "Ensalada", "de puñetazos", 5.0, DishType.STARTER, restaurantSpain);
        Dish plato2 = new Dish(null, "Lentejas", "con chorizo", 8.0, DishType.MAIN, restaurantSpain);
        Dish plato3 = new Dish(null, "Tarta de queso", null, 7.50, DishType.DESSERT, restaurantSpain);
        Dish plato4 = new Dish(null, "Champán", null, 60.0, DishType.DESSERT, restaurantSpain);
        dishRepository.saveAll(List.of(plato1, plato2, plato3, plato4));

        // OPcion  1: crear consultas personalizadas en DishRepository
        // que traiga los platos con precio menor que 10 euros findAllByPrice...
        for (var plato: dishRepository.findByPriceLessThanEqual(7.99))
            System.out.println("plato");
        // que traiga los platos de un restaurante ordenados por precio ascendente findAllBy
        // que traiga aquellos platos que no contengan alergenos


        System.out.println("TRAER PLATOS DE UN RESTAURANTE ORDENADOS POR PRECIO ASCENDENTE:");
        Long restaurantId = restaurantSpain.getId();
        for (var plato: dishRepository.findByRestaurantIdOrderByPrice(restaurantId))
            System.out.println(plato);



        // Opción 2: crear un pedido
        Order pedido1 = new Order();
        pedido1.setNumPeople(2);
        pedido1.setRestaurant(restaurantSpain);
        pedido1.setTableNumber(5);
        pedido1.setTip(2.33);
        //pedido1.setTotalPrice(57.5);
        //pedido1.setDate(LocalDateTime.now()
        Order pedido2 = new Order();
        pedido2.setNumPeople(4);
        pedido2.setRestaurant(restaurantSpain);
        pedido2.setTableNumber(2);
        pedido2.setTip(1.33);

        Order pedido3 = new Order(6.44, 6, 6, restaurantSpain);

        Order pedido4 = new Order();
        pedido4.setNumPeople(3);
        pedido4.setRestaurant(restaurantSpain);
        pedido4.setTableNumber(1);
        pedido4.setTip(2.88);
        orderRepository.save(pedido4);
        orderRepository.save(pedido3);
        orderRepository.save(pedido2);
        orderRepository.save(pedido1);

        //crear 6 líneas de pedido, una para cada Order.
        //OrderLine...
        OrderLine unaEnsalada = new OrderLine(1, pedido1, plato1);//Creamos linea pedido que apunte al pedido y plato)
        OrderLine dosLentejas = new OrderLine(2, pedido2, plato2);
        OrderLine dosTartas = new OrderLine(6, pedido3, plato3);
        OrderLine botellaChampan = new OrderLine(3, pedido4, plato4);
         orderLineRepository.saveAll(List.of(unaEnsalada, dosLentejas, dosTartas, botellaChampan));













        //save()
        //saveAll()

        //deleteById
        //deleteAll
    }
}
