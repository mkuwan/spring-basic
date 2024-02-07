package org.example.springbasic.service;

import org.example.springbasic.repository.entity.Book;
import org.example.springbasic.repository.jpa.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceTest {

    @Mock
    private BookRepository bookRepositoryMock;


    @Autowired
    private BookRepository bookRepository;


    /**
     * 正常系
     * BookService.getBookList()のテスト
     * BookRepository.findAll()のモックを作成し、BookModelのリストを返却する
     * 期待値: BookModelのリストが返却されること
     * 期待値: BookModelのリストの要素数が10であること
     * 期待値: BookModelのリストの各要素のtitleが"title" + iであること
     * 期待値: BookRepository.findAll()が呼ばれたこと
     */
    @Test
    void getBookList_WithMock_Success() {
        // arrange
        // BookModelのリストを作成
        List<Book> books = new ArrayList<>();
        // BookModelのリストに10個のBookModelを追加
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            // bookIdにUUIDを設定
            book.setBookId(UUID.randomUUID().toString());
            // titleに"title" + iを設定
            book.setTitle("title" + i);
            // BookModelのリストに追加
            books.add(book);
        }
        // BookRepository.findAll()のモックを作成
        // BookRepository.findAll()が呼ばれたら、booksを返却する
        when(bookRepositoryMock.findAll()).thenReturn(books);
        // BookServiceのインスタンスを作成
        BookService bookService = new BookService(bookRepositoryMock);

        // act
        // BookService.getBookList()を呼び出し
        var result = bookService.getBookList();

        // assert
        // BookModelのリストが返却されること
        assertNotNull(result);
        // BookModelのリストの要素数が10であること
        assertEquals(10, result.size());
        // BookModelのリストの各要素のtitleが"title" + iであること
        for (int i = 0; i < 10; i++) {
            assertEquals("title" + i, result.get(i).getTitle());
        }
        // BookRepository.findAll()が呼ばれたこと
        verify(bookRepositoryMock, times(1)).findAll();
        Assertions.assertThrows(Exception.class, () -> {
            throw new Exception("test");
        });
    }


    /**
     * 正常系
     * BookService.getBooks()のテスト
     * BookRepositoryはモック化せず、実際のインスタンスを使用する(実際のDBにアクセス)
     * 期待値: BookModelのリストが返却されること
     * 期待値: BookModelのリストの要素数が5であること
     * 期待値: BookModelのリストの各要素のtitleが"テストタイトル" + iであること
     * 期待値: BookRepository.findAll()が呼ばれたこと
     * 期待値: BookRepository.findAll()が1回だけ呼ばれたこと
     */
    @Test
    void getBookList_WithRepository_Success(){
        // arrange
        // BookModelのリストを作成
        List<Book> books = new ArrayList<>();
        // BookModelのリストに5個のBookModelを追加
        for (int i = 0; i < 5; i++) {
            Book book = new Book();
            // bookIdにUUIDを設定
            book.setBookId(UUID.randomUUID().toString());
            // titleに"テストタイトル" + iを設定
            book.setTitle("テストタイトル" + i);
            // BookModelのリストに追加
            books.add(book);
        }
        bookRepository.saveAll(books);

        // BookServiceのインスタンスを作成
        BookService bookService = new BookService(bookRepository);

        // act
        // BookService.getBookList()を呼び出し
        var result = bookService.getBookList();

        // assert
        // BookModelのリストが返却されること
        assertNotNull(result);
        // BookModelのリストの要素数が5であること
        assertEquals(5, result.size());
        // BookModelのリストの各要素のtitleが"テストタイトル" + iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("テストタイトル" + i, result.get(i).getTitle());
        }
        // BookRepository.findAll()が呼ばれたこと
        verify(bookRepository, times(1)).findAll();

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