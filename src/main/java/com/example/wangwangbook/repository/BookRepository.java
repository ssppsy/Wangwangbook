package com.example.wangwangbook.repository;

import com.example.wangwangbook.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findTop10ByOrderByRatingDesc();
    List<Book> findTop10ByCategoryOrderByRatingDesc(String category);
    List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
    // 查找某一类别的图书
    List<Book> findByCategoryIgnoreCase(String genre);
    // 获取所有唯一类别（用于导航栏）
    @Query("SELECT DISTINCT b.category FROM Book b")
    List<String> findDistinctCategory();

    List<Book> findByCategoryContainingIgnoreCase(String category);
}

