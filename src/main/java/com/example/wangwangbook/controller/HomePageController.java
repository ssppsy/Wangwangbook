package com.example.wangwangbook.controller;

import com.example.wangwangbook.entity.Book;
import com.example.wangwangbook.entity.User;
import com.example.wangwangbook.repository.BookRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.stereotype.Controller;

@Controller
public class HomePageController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        model.addAttribute("recommendations", bookRepository.findTop10ByOrderByRatingDesc());
        model.addAttribute("monthly", bookRepository.findTop10ByCategoryOrderByRatingDesc("monthly"));
        model.addAttribute("top", bookRepository.findTop10ByCategoryOrderByRatingDesc("top"));
        model.addAttribute("foreign", bookRepository.findTop10ByCategoryOrderByRatingDesc("foreign"));
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            model.addAttribute("username", user.getEmail());
        }

        return "index";
    }
}
