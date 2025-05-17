package com.example.wangwangbook.controller;

import com.example.wangwangbook.entity.Book;
import com.example.wangwangbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books/{id}")
    public String getBookDetail(@PathVariable Long id, Model model) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book == null) {
            return "error/404";
        }
        model.addAttribute("book", book);
        return "book-detail";
    }
}
