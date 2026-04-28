package com.example.Controller;

import com.example.model.Dish;
import com.example.model.Review;
import com.example.repository.DishRepository;
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
public class DishController {
    //Agregamos el repositorio que queramos

    private DishRepository dishRepository;
    private ReviewRepository reviewRepository;

    //Agregamos el constructor

    @GetMapping("dishes/{id}")
    //Creamos metodo
    public String dishDetail(@PathVariable Long id, Model model){
        Optional<Dish> dishOptional = dishRepository.findById(id);
        if(dishOptional.isPresent()){
            Dish dish = dishOptional.get();
            model.addAttribute("dish", dish);
            //Traer reviews de este plato y cargar en model
            List<Review> reviews = reviewRepository.findByDish_IdOrderByCreationDateDesc(id);
            model.addAttribute("reviews", reviews);//Poder traer reviews de un plato

            return "dishes/dish-detail";
        }
        return "redirect:/restaurants";
    }
}
