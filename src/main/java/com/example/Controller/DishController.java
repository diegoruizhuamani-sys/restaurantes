package com.example.Controller;

import com.example.model.Dish;
import com.example.repository.DishRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class DishController {
    //Agregamos el repositorio que queramos

    private DishRepository dishRepository;

    //Agregamos el constructor

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }
    @GetMapping("dishes/{id}")
    //Creamos metodo
    public String dishDetail(@PathVariable Long id, Model model){
        Optional<Dish> dishOptional = dishRepository.findById(id);
        if(dishOptional.isPresent()){
            Dish dish = dishOptional.get();
            model.addAttribute("dish", dish);
            return "dishes/dish-detail";
        }
        return "redirect:/restaurants";
    }
}
