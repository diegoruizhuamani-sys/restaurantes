package com.example.Controller;

import com.example.model.Restaurante;
import com.example.repository.RestauranteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class RestauranteController {
    // inyectar el restaurant repository
    private final RestauranteRepository restauranteRepository;

    public RestauranteController(RestauranteRepository restaurantRepository) {
        this.restauranteRepository = restaurantRepository;
    }

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
    public String restaurantDetail(@PathVariable Long id, Model model){

        // buscar restaurante por su id: findById

        Optional<Restaurante> restauranteOptional = restauranteRepository.findById(id);
        if (restauranteOptional.isPresent()){
            //El restaurante si existe
            Restaurante restaurante = restauranteOptional.get();
            model.addAttribute("restaurante", restaurante);
            return "restaurants/restaurant-detail";
        } else{
            // El restaurante NO existe
            return "redirect:/restaurants"; //redirect: lenguaje especial de controladores
        }

        // cargar ese restaurante en el model




    }
}
