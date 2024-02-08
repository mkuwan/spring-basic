package org.example.springbasic.repository.jpa;

import org.example.springbasic.repository.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setup(){
        bookRepository.deleteAll();

        for (int i = 0; i < 5; i++) {
            var book = new Book();
            book.setBookId(UUID.randomUUID().toString());
            book.setTitle("RepositoryTest用の書籍" + i);
            book.setTitleKana("titleKana" + i);
            book.setVersion("version" + i);
            book.setSeries("series" + i);
            book.setAuthor("author" + i);
            book.setPublisher("publisher" + i);
            book.setPublishYear(2000 + i);
            book.setSizeInfo("sizeInfo" + i);
            book.setIsbn("isbn" + i);
            book.setAmount(i);
            book.setCanRent(true);
            bookRepository.save(book);
        }
    }

    @Test
    void findBooksByTitle() {
        // arrange
        String title = "positoryTest用";
        // act
        var books = bookRepository.findBooksByTitle(title);
        // assert
        assertEquals(5, books.size());
    }
}