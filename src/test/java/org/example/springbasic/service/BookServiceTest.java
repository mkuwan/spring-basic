package org.example.springbasic.service;

import org.example.springbasic.repository.entity.Book;
import org.example.springbasic.repository.jpa.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;


    @Test
    void getBookList() {
        // arrange
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setTitle("title" + i);
            books.add(book);
        }
        when(bookRepository.findAll()).thenReturn(books);

        BookService bookService = new BookService(bookRepository);

        // act
        var result = bookService.getBookList();

        // assert
        assertEquals(10, result.size());
        for (int i = 0; i < 10; i++) {
            assertEquals("title" + i, result.get(i).getTitle());
        }

    }

    @Test
    void getBook() {
    }

    @Test
    void addBook() {
    }

    @Test
    void deleteBook() {
    }

    @Test
    void updateBook() {
    }
}