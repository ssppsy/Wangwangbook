package com.example.wangwangbook.controller;

import com.example.wangwangbook.entity.Book;
import com.example.wangwangbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/search")
    public String searchBooks(@RequestParam("query") String query, Model model) {
        List<Book> results = bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
        model.addAttribute("results", results);
        model.addAttribute("keyword", query);
        return "search-results";
    }
}
