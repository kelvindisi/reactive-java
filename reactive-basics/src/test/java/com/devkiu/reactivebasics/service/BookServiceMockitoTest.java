package com.devkiu.reactivebasics.service;

import com.devkiu.reactivebasics.exception.BookException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class BookServiceMockitoTest {
    @Mock
    private BookInfoService bookInfoService;
    @Mock
    private ReviewService reviewService;
    @InjectMocks
    private BookService bookService;

    @Test
    void getBooksMock() {
        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenCallRealMethod();

        var books = bookService.getBooks();

        StepVerifier.create(books)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void getBooksMockOnError() {
        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("Exception occurred"));

        var books = bookService.getBooks();

        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();
    }
}