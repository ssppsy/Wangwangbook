package com.example.wangwangbook.repository;

import com.example.wangwangbook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findTop10ByOrderByRatingDesc();
    List<Book> findTop10ByCategoryOrderByRatingDesc(String category);

    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
}

