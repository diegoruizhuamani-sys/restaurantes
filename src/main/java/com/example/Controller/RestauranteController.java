package com.example.Controller;

import com.example.model.Dish;
import com.example.model.Enum.FoodType;
import com.example.model.Restaurante;
import com.example.model.Review;
import com.example.repository.DishRepository;
import com.example.repository.RestauranteRepository;
import com.example.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Controller
public class RestauranteController {
    // inyectar el restaurant repository
    private final RestauranteRepository restauranteRepository;
    private final DishRepository dishRepository;
    private final ReviewRepository reviewRepository;

//    // http://localhost:8080/restaurants
//    @GetMapping("restaurants") // controlador
//    public String restaurantList(Model model) {
//        // cargar datos en el modelo
    ////        List<Restaurant> restaurants = restaurantRepository.findAll();
//        List<Restaurant> restaurants = restaurantRepository.findByActiveTrue();
//        model.addAttribute("restaurants", restaurants);
//        model.addAttribute("numRestaurants", restaurants.size());
//        model.addAttribute("title", "Lista de restaurantes");
//        return "restaurants/restaurant-list"; // vista
//    }
    // http://localhost:8080/restaurants?foodType=SPANISH
    @GetMapping("restaurants") // controlador
    public String restaurantList(
            Model model,
            @RequestParam(required = false) FoodType foodType,
            @RequestParam(required = false) Double price
    ) {
        List<Restaurante> restaurants = restauranteRepository.findActiveFiltering(foodType, price);
        model.addAttribute("restaurants", restaurants);
        model.addAttribute("numRestaurants", restaurants.size());
        model.addAttribute("title", "Lista de restaurantes");
        return "restaurants/restaurante-list"; // vista
    }

    // nuevo metodo para traer un solo restaurante por su id
    @GetMapping("restaurants/{id}")
    public String restaurantDetail(@PathVariable Long id, Model model) {


        // buscar restaurante por su id: findById
//        Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
        Optional<Restaurante> restaurantOptional = restauranteRepository.findByIdAndActiveTrue(id);
        if (restaurantOptional.isPresent()) {


            // El restaurante sí existe
            Restaurante restaurant = restaurantOptional.get();
            model.addAttribute("restaurant", restaurant);
            // opcional:
            // cargar los platos (Dish) de este restaurant en el model
            List<Dish> platos = dishRepository.findByRestaurantIdOrderByPrice(restaurant.getId());
            model.addAttribute("dishes", platos);

            // reviews
            //List<Review> reviews = reviewRepository.findAll();
            List<Review> reviews = reviewRepository.findByRestaurante_IdOrderByCreationDateDesc(restaurant.getId());
            model.addAttribute("reviews", reviews); // accesibles desde HTML

            return "restaurants/restaurant-detail";

        }

        // El restaurante NO existe
        // CUIDADO no apunta a HTML
        // APUNTA al Controller
        return "redirect:/restaurants";
    }

    @GetMapping("restaurants/deactivate/{id}")
    public String restaurantDeactivate(@PathVariable Long id, Model model) {
        Optional<Restaurante> restaurantOptional = restauranteRepository.findById(id);

        if(restaurantOptional.isPresent()) {
            Restaurante restaurant = restaurantOptional.get();
            restaurant.setActive(false);
            restauranteRepository.save(restaurant);
        }

        return "redirect:/restaurants";
    }
}