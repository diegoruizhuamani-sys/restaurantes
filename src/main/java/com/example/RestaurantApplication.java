package com.example;

import com.example.model.*;
import com.example.model.Enum.DishType;
import com.example.model.Enum.FoodType;
import com.example.model.Enum.OrderStatus;
import com.example.repository.*;
import org.jspecify.annotations.NonNull;
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

        // obtener los repositorios para poder hacer operaciones de base de datos con ellos
        // Los repositorios nos dan las operaciones CRUD (findAll, findById, save, delete)
        RestauranteRepository restauranteRepository = context.getBean(RestauranteRepository.class);
        EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
        DishRepository dishRepository = context.getBean(DishRepository.class);
        OrderRepository orderRepository = context.getBean(OrderRepository.class);
        OrderLineRepository orderLineRepository = context.getBean(OrderLineRepository.class);
        ReviewRepository reviewRepository = context.getBean(ReviewRepository.class);
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
        rest2.setActive(false);
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

        // obtener todos los restaurantes de base de datos
        // SELECT * from restaurantes;
        List<Restaurante> restaurantes = restauranteRepository.findAll();
         System.out.println(restaurantes);
//        for (int i = 0; i < restaurantes.size(); i++) { // size() nos dice el número de elementos en la lista
//            System.out.println(restaurantes.get(i));
//        }
        for (Restaurante restaurant : restaurantes) { // bucle foreach itera uno a uno los restaurantes sin crear un index
            System.out.println(restaurant);
        }


        List<Employee> empleados = employeeRepository.findAll();
        // System.out.println(empleados);
        for (Employee empleado : empleados) {
            System.out.println(empleado);
        }

        // saveAll
        List<Restaurante> sitiosGuaposParaComer = getRestaurantes();
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
        System.out.println("restaurante con id " + 3L + " existe : " + restauranteRepository.existsById(3L));
        //restauranteRepository.deleteById(rest2.getId());

        //delete, borra pasando el objeto
       // restauranteRepository.delete(DiegoBar);

        //findById traer un restaurante/empleado por su id
        Long idABuscar = 2L;
        // Restaurante restauranteFromDatabase = restauranteReposity.findById(idABuscar);
        Optional<Restaurante> restauranteFromDatabase = restauranteRepository.findById(idABuscar);
        // var restauranteFromDatabase = restauranteRepository.findById(idABuscar);
        restauranteFromDatabase.ifPresent(System.out::println);

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
        //Restaurant restaurantItalian =  new Restaurant();
        //restaurantItalian.setFoodType(FoodType.ITALIAN); // da fallo porque no existe el valor ITALIAN en el enum FoodType

        // Probar fecha de startDAte del restuarante
        Restaurante smashBurguer = new Restaurante();
        smashBurguer.setName("Smash Burguer Madrid");
        smashBurguer.setStartDate(LocalDate.now()); // fecha actual
        restauranteRepository.save(smashBurguer);
        System.out.println(smashBurguer);

        // fecha futura
        Restaurante sidreria = new Restaurante();
        sidreria.setName("Sidreria");
        sidreria.setStartDate(LocalDate.of(2026, 6, 25));
        restauranteRepository.save(sidreria);
        System.out.println(sidreria);
        // LocalDate solo da año mes y día
        // LocalTime solo da hora minuto y segundo
        // LocalDateTime da año mes día hora minuto segundo

        // MANY TO ONE - ASOCIAR UN RESTAURANTE A DOS EMPLEADOS
        // Paso 1. crear restaurante y guardarlo
        Restaurante dominosPizza = new Restaurante();
        dominosPizza.setName("DominosPizza");
        dominosPizza.setFoodType(FoodType.SPANISH); // ponemos SPANISH para la prueba, aunque no lo sea
        restauranteRepository.save(dominosPizza);
        // paso 2. crear empleados, setRestaurant y guardar
        Employee juanito = new Employee();
        juanito.setFirstName("Juanito");
        juanito.setRestaurante(dominosPizza);
        juanito.setAge(18); // NO cumple el filtro de findByAgeGreaterThanEqual
        employeeRepository.save(juanito);
        System.out.println(juanito); // imprime el id del restaurante en el toSTring
        Employee patricia = new Employee();
        patricia.setFirstName("patricia");
        patricia.setRestaurante(dominosPizza);
        patricia.setAge(35); // SÍ cumple el filtro de findByAgeGreaterThanEqual
        employeeRepository.save(patricia);
        System.out.println(patricia);



        // Bucle for para iterar sobre todos los empleados imprimiendo el nombre del empleado y el nombre de su restaurante
        // si lo tiene
        List<Employee> trabajadores = employeeRepository.findAll();
        for (Employee trabajador : trabajadores) {
//            System.out.println(
//                    trabajador.getFirstName() +
//                    " trabaja en " +
//                    (trabajador.getRestaurant() != null ? trabajador.getRestaurant().getName() : "ningún sitio"));

            if (trabajador.getRestaurante() != null)  {
                System.out.println(trabajador.getFirstName() + " trabaja en " + trabajador.getRestaurante().getName());
            } else {
                System.out.println(trabajador.getFirstName() + " trabaja en ningún sitio");
            }
        }
        // probar a filtrar por nombre de restaurante
        List<Employee> empleadosDominos = employeeRepository.findByRestauranteName("DominosPizza");
        System.out.println(empleadosDominos);

        System.out.println("FILTRAR EMPLEADOS POR TIPO DE COMIDA DE RESTAURANTE:");
        for (var e : employeeRepository.findByRestaurante_FoodType(FoodType.SPANISH))
            System.out.println(e);

        System.out.println("FILTRAR EMPLEADOS POR EDAD MAYOR O IGUAL QUE");
        for (var e : employeeRepository.findByAgeGreaterThanEqual(20))
            System.out.println(e);

        System.out.println("TRAER TODOS LOS EMPLEADOS ORDENADOS POR NOMBRE ASCENDENTE A-Z");
        for (var e : employeeRepository.findByOrderByFirstNameAsc())
            System.out.println(e);

        String nombre = "Alan"; // string normal
        // Text block, string con triple comilla para tener varias líneas, ideal para queries largas en repositorios
        String descripcionLarga = """
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
                Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                """;

        // CREAR PLATOS Y GUARDARLOS
        Dish plato1 = new Dish(null, "Ensalada", "de puñetazos", 5.0, DishType.STARTER, BarDiego);
        Dish plato2 = new Dish(null, "Lentejas", "con chorizo", 8.0, DishType.MAIN, BarDiego);
        Dish plato3 = new Dish(null, "Tarta de queso", null, 7.50, DishType.DESSERT, BarDiego);
        Dish plato4 = new Dish(null, "Champán", null, 60.0, DishType.DESSERT, BarDiego);
        dishRepository.saveAll(List.of(plato1, plato2, plato3, plato4));

        // OPcion  1: crear consultas personalizadas en DishRepository
        // que traiga los platos con precio menor que 10 euros findAllByPrice...
        for (var plato: dishRepository.findByPriceLessThanEqual(7.99))
            System.out.println(plato);
        // que traiga los platos de un restaurante ordenados por precio ascendente findAllBy
        // que traiga aquellos platos que no contengan alergenos


        System.out.println("TRAER PLATOS DE UN RESTAURANTE ORDENADOS POR PRECIO ASCENDENTE:");
        Long restaurantId = BarDiego.getId();
        for (var plato: dishRepository.findByRestaurantIdOrderByPrice(restaurantId))
            System.out.println(plato);


        // Crear pedido
        Order pedido1 = new Order();
        pedido1.setNumPeople(2);
        pedido1.setTableNumber(1);
        pedido1.setRestaurant(BarDiego);
        pedido1.setTip(2.33);
        //pedido1.setDate(LocalDateTime.now());
        orderRepository.save(pedido1);
//        Order pedido2 = new Order(4.23, 2, 4, restaurantSpain);
//        orderRepository.save(pedido2);

        // crear 6 lineas de pedido, una para cada Order.
        // OrderLine .... (cantidad, pedido, plato)
        OrderLine unaEnsalada = new OrderLine(1, pedido1, plato1); // una ensalada
        OrderLine dosLentejas = new OrderLine(2, pedido1, plato2); // dos platos de lentejas
        OrderLine dosTartas = new OrderLine(2, pedido1, plato3); // dos tartas de queso

        List<OrderLine> lineasPedido = orderLineRepository.saveAll(List.of(unaEnsalada, dosLentejas, dosTartas));

        // calcular precio total en java:
        double totalPrice = 0.0;
        for (OrderLine lineaPedido : lineasPedido) {
            // sacar el precio del plato
            double precioLinea = lineaPedido.getDish().getPrice() * lineaPedido.getQuantity();
            totalPrice += precioLinea;
        }

        // guardar el totalPrice en base de datos:
        pedido1.setTotalPrice(totalPrice);
        pedido1.setStatus(OrderStatus.FINISHED); // marcamos el pedido como completado
        orderRepository.save(pedido1); // actualizar el totalPrice del pedido para saber cuanto dinerito hemos ganado

        // calcular precio total directamente en base de datos con una query
        Double totalPrice2 = orderLineRepository.calculateTotalPrice(pedido1.getId()); // JPQL
        /*
        Ambos precios son el mismo
        El primero totalPrice, se calcula desde java con un bucle for
        El segundo totalPrice2, se calcula directamente en base de datos por lo que sería más óptimo
         */
        System.out.println("Precio totalPrice: " + totalPrice);
        System.out.println("Precio totalPrice2: " + totalPrice2);



        // crear cuatro reviews de un restaurante usando Builder de lombok
        Review review1 = Review.builder()
                .description("Te atienden bien")
                .restaurante(BarDiego)
                .title("Restaurante espectacular")
                .rating(5)
                .build();

        Review review2 = Review.builder()
                .description("Nefasto")
                .restaurante(BarDiego)
                .title("Me sirvieron la sopa sin mosca")
                .rating(1)
                .build();

        Review review3 = Review.builder()
                .description("Ni fu ni fa")
                .restaurante(BarDiego)
                .title("Comí y no me morí")
                .rating(3)
                .build();


        Review review4 = Review.builder()
                .description("Ni fu ni fa")
                .dish(plato1)
                .title("Me pusieron de menos")
                .rating(2)
                .build();

        Review review5 = Review.builder()
                .description("Excelente")
                .dish(plato1)
                .title("Guay")
                .rating(5)
                .build();

        reviewRepository.saveAll(List.of(review1, review2, review3, review4,  review5));
        // resumen
        // findAll
        // findById
        // existById
        // count()

        // save()
        // saveAll()

        // deleteById
        // deleteALl


    }

    private static @NonNull List<Restaurante> getRestaurantes() {
        Restaurante r1 = new Restaurante("DiegoBar", 10.0, 3);
        Restaurante r2 = new Restaurante("R2", 15.0, 4);

        // opción clásica para crear lista:
        List<Restaurante> sitiosParaComer = new ArrayList<>(); // crear una lista vacía
        sitiosParaComer.add(r1); // añadir un restaurante a la lista
        sitiosParaComer.add(r2); // añadir un restaurante a la lista
        List<String> alumnos = new ArrayList<>(); // crear una lista vacía
        List<Double> precios = new ArrayList<>(); // crear una lista vacía

        // opción moderna para crear lista:
        List<Restaurante> sitiosGuaposParaComer = List.of(r1, r2);
        return sitiosGuaposParaComer;
    }
}