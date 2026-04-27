package com.example.Controller;

import com.example.model.Dish;
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

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Controller
public class RestauranteController {
    // inyectar el restaurant repository
    private final RestauranteRepository restauranteRepository;
    private final DishRepository dishRepository;
    private final ReviewRepository reviewRepository;

//    public RestauranteController(RestauranteRepository restaurantRepository, DishRepository dishRepository, ReviewRepository reviewRepository) {
//        this.restauranteRepository = restaurantRepository;
//        this.dishRepository = dishRepository;
//        this.reviewRepository = reviewRepository;
//
//    }

    /*
    Resumen de métodos típicos en una clase controller:
    @GetMapping("restaurante") findAll
    @GetMapping("restaurantes/id") findById

    @GetMapping("restaurante/create") createForm
    @PostMapping("restaurantes/create") create

    @GetMapping("restaurantes/{id}/edit") editForm
    @PostMapping("restaurantes/{id}/edit") edit

    @GetMapping("restaurantes/delete/{id}") delete
     */
    // get all restaurants
    // http://localhost:8080/restaurants
    @GetMapping("/restaurants")
    public String restauranteList(Model model) {
        // cargar datos en el modelo
        List<Restaurante> restaurants = restauranteRepository.findAll();
        model.addAttribute("restaurants", restauranteRepository.findAll());
        model.addAttribute("title","Lista de restaurantes");
        model.addAttribute("numRestaurants","Lista de restaurantes");

        return "restaurants/restaurante-list";
    }


    //nuevo metodo para traer un solo restaurante por su id
    @GetMapping("restaurants/{id}")
    public String restaurantDetail(@PathVariable Long id, Model model) {

        // buscar restaurante por su id: findById

        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);
        if (restauranteOptional.isPresent()) {
            //El restaurante si existe
            Restaurante restaurante = restauranteOptional.get();
            model.addAttribute("restaurante", restaurante);

            // opcional:
            // cargar los platos Dish
            List<Dish> platos = dishRepository.findByRestaurantIdOrderByPrice(restaurante.getId());
            model.addAttribute("dishes", platos);

//Añadir reviews
            List<Review> reviews = reviewRepository.findByRestaurante_IdOrderByCreationDateDesc(restaurante.getId());
            model.addAttribute("reviews", reviews);//accesibles desde HTML

            return "restaurants/restaurant-detail";
        }
        //redirect: lenguaje especial de controladores
        // cargar ese restaurante en el model
        // El restaurante NO existe
        // CUIDADO no apunta a HTML
        // APUNTA al Controller
        return "redirect:/restaurants";
    }
}
