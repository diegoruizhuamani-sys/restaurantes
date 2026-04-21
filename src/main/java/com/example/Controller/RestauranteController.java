package com.example.Controller;

import com.example.model.Restaurante;
import com.example.repository.RestauranteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class RestauranteController {
    // inyectar el restaurant repository
    private final RestauranteRepository restauranteRepository;

    public RestauranteController(RestauranteRepository restaurantRepository) {
        this.restauranteRepository = restaurantRepository;
    }

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
}
