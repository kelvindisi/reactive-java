package com.devkiu.reactivebasics.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {
    private final BookInfoService bookInfoService = new BookInfoService();
    private final ReviewService reviewService = new ReviewService();
    private final BookService bookService = new BookService(bookInfoService, reviewService);

    @Test
    void getBooks() {
        var books = bookService.getBooks();

        StepVerifier.create(books)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void getBookById() {
        var book = bookService.getBookById(1);

        StepVerifier.create(book)
                .assertNext(book1 -> {
                    assertEquals("Python for Dummies", book1.getBookInfo().getTitle());
                })
                .verifyComplete();
    }
}