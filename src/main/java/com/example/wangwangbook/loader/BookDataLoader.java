package com.example.wangwangbook.loader;


import com.example.wangwangbook.entity.Book;
import com.example.wangwangbook.repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.*;

@Component
public class BookDataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Value("${bookdata.load.enabled:false}")
    private boolean loadEnabled;

    public BookDataLoader(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!loadEnabled) {
            System.out.println("📕 Book data loading is disabled.");
            return;
        }

        RestTemplate restTemplate = new RestTemplate();

        for (int page = 1; page <= 2; page++) {
            String url = "https://openlibrary.org/search.json?q=subject:fiction&page=" + page;

            try {
                ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
                List<Map<String, Object>> docs = (List<Map<String, Object>>) response.getBody().get("docs");

                for (Map<String, Object> doc : docs) {
                    String title = (String) doc.get("title");
                    List<String> authors = (List<String>) doc.get("author_name");
                    Integer coverId = (Integer) doc.get("cover_i");

                    if (title == null || authors == null || coverId == null) {
                        continue;
                    }

                    Book book = new Book();
                    book.setTitle(title);
                    book.setAuthor(authors.get(0));
                    book.setImageUrl("https://covers.openlibrary.org/b/id/" + coverId + "-L.jpg");
                    book.setDescription("Imported from Open Library");
                    book.setCategory("fiction");
                    book.setRating(Math.random() * 5);

                    bookRepository.save(book);
                }

                System.out.println("✅ Page " + page + " done.");

            } catch (Exception e) {
                System.out.println("❌ Failed to load page " + page + ": " + e.getMessage());
            }

            Thread.sleep(50);
        }
    }
}

