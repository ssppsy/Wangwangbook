package com.example.wangwangbook.entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "book")
public class Book {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    private String category;
    private Double rating;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

}

