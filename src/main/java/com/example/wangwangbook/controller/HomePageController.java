package com.example.wangwangbook.controller;

import com.example.wangwangbook.entity.Book;
import com.example.wangwangbook.entity.User;
import com.example.wangwangbook.repository.BookRepository;
import com.example.wangwangbook.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomePageController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public String index(Model model, HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            model.addAttribute("username", user.getEmail());

            String genre = user.getGenre();
            List<Book> genreBooks = bookRepository.findTop10ByCategoryOrderByRatingDesc(genre);
            if (genreBooks.isEmpty()) {
                genreBooks = bookRepository.findTop10ByOrderByRatingDesc(); // fallback
            }
            model.addAttribute("recommendations", genreBooks);
        } else {
            // 未登录用户展示默认推荐
            model.addAttribute("recommendations", bookRepository.findTop10ByOrderByRatingDesc());
        }


        // 这三类推荐始终显示
        model.addAttribute("monthly", bookRepository.findTop10ByCategoryOrderByRatingDesc("monthly"));
        model.addAttribute("top", bookRepository.findTop10ByCategoryOrderByRatingDesc("top"));
        model.addAttribute("foreign", bookRepository.findTop10ByCategoryOrderByRatingDesc("foreign"));

        // 添加动态分类列表
        model.addAttribute("genres", bookRepository.findDistinctCategory());

        return "index";
    }

    @GetMapping("/genre/{genre}")
    public String booksByGenre(@PathVariable String genre, Model model) {
        List<Book> books = bookRepository.findByCategoryIgnoreCase(genre);
        model.addAttribute("books", books);
        model.addAttribute("genre", genre);
        model.addAttribute("genres", bookRepository.findDistinctCategory());
        return "genre-page";
    }

}
