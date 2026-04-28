package com.example.Controller;

import com.example.repository.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
public class ReviewController {
    private final ReviewRepository reviewRepository;

    @GetMapping("reviews")

    public String reviews (Model model) {

        model.addAttribute("reviews", reviewRepository.findAll());

        return "reviews/review-list";
    }

}
