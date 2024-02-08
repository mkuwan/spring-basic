package org.example.springbasic.repository.jpa;

import org.example.springbasic.repository.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookRepositoryTest {

    @Mock
    private BookRepository bookRepositoryMock;

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

    /**
     * 正常系
     * Mockを使用してBookRepository.findBooksByTitle()のテスト
     * あいまい検索でtitleに"positoryTest用"を指定して書籍を検索する
     * 期待値: 書籍が5件返却されること
     * 期待値: 返却された書籍のtitleが全て"RepositoryTest用の書籍"+iであること
     * 期待値: 返却された書籍のtitleKanaが全て"titleKana"+iであること
     * 期待値: 返却された書籍のversionが全て"version"+iであること
     * 期待値: 返却された書籍のseriesが全て"series"+iであること
     * 期待値: 返却された書籍のauthorが全て"author"+iであること
     * 期待値: 返却された書籍のpublisherが全て"publisher"+iであること
     * 期待値: 返却された書籍のpublishYearが全て2000+iであること
     * 期待値: 返却された書籍のsizeInfoが全て"sizeInfo"+iであること
     * 期待値: 返却された書籍のisbnが全て"isbn"+iであること
     * 期待値: 返却された書籍のamountが全てiであること
     * 期待値: 返却された書籍のcanRentが全てtrueであること
     * 期待値: BookRepository.findBooksByTitle()が呼ばれたこと
     */
    @Test
    void findBooksByTitle_WithMock_Success(){
        // arrange
        String title = "positoryTest用";

        when(bookRepositoryMock.findBooksByTitle(title)).thenReturn(bookRepository.findBooksByTitle(title));

        // act
        var books = bookRepositoryMock.findBooksByTitle(title);

        // assert
        // 期待値: 書籍が5件返却されること
        assertEquals(5, books.size());

        // 期待値: 返却された書籍のtitleが全て"RepositoryTest用の書籍"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("RepositoryTest用の書籍" + i, books.get(i).getTitle());
        }

        // 期待値: 返却された書籍のtitleKanaが全て"titleKana"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("titleKana" + i, books.get(i).getTitleKana());
        }

        // 期待値: 返却された書籍のversionが全て"version"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("version" + i, books.get(i).getVersion());
        }

        // 期待値: 返却された書籍のseriesが全て"series"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("series" + i, books.get(i).getSeries());
        }

        // 期待値: 返却された書籍のauthorが全て"author"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("author" + i, books.get(i).getAuthor());
        }

        // 期待値: 返却された書籍のpublisherが全て"publisher"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("publisher" + i, books.get(i).getPublisher());
        }

        // 期待値: 返却された書籍のpublishYearが全て2000+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals(2000 + i, books.get(i).getPublishYear());
        }

        // 期待値: 返却された書籍のsizeInfoが全て"sizeInfo"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("sizeInfo" + i, books.get(i).getSizeInfo());
        }

        // 期待値: 返却された書籍のisbnが全て"isbn"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("isbn" + i, books.get(i).getIsbn());
        }

        // 期待値: 返却された書籍のamountが全てiであること
        for (int i = 0; i < 5; i++) {
            assertEquals(i, books.get(i).getAmount());
        }

        // 期待値: 返却された書籍のcanRentが全てtrueであること
        for (int i = 0; i < 5; i++) {
            assertTrue(books.get(i).getCanRent());
        }

        // 期待値: BookRepository.findBooksByTitle()が呼ばれたこと
        verify(bookRepositoryMock, times(1)).findBooksByTitle(title);

    }

    /**
     * 正常系
     * BookRepository.findBooksByTitle()のテスト
     * あいまい検索でtitleに"positoryTest用"を指定して書籍を検索する
     * 期待値: 書籍が5件返却されること
     * 期待値: 返却された書籍のtitleが全て"RepositoryTest用の書籍"+iであること
     * 期待値: 返却された書籍のtitleKanaが全て"titleKana"+iであること
     * 期待値: 返却された書籍のversionが全て"version"+iであること
     * 期待値: 返却された書籍のseriesが全て"series"+iであること
     * 期待値: 返却された書籍のauthorが全て"author"+iであること
     * 期待値: 返却された書籍のpublisherが全て"publisher"+iであること
     * 期待値: 返却された書籍のpublishYearが全て2000+iであること
     * 期待値: 返却された書籍のsizeInfoが全て"sizeInfo"+iであること
     * 期待値: 返却された書籍のisbnが全て"isbn"+iであること
     * 期待値: 返却された書籍のamountが全てiであること
     * 期待値: 返却された書籍のcanRentが全てtrueであること
     */
    @Test
    void findBooksByTitle_Success() {
        // arrange
        String title = "positoryTest用";

        // act
        var books = bookRepository.findBooksByTitle(title);

        // assert
        // 期待値: 書籍が5件返却されること
        assertEquals(5, books.size());

        // 期待値: 返却された書籍のtitleが全て"RepositoryTest用の書籍"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("RepositoryTest用の書籍" + i, books.get(i).getTitle());
        }

        // 期待値: 返却された書籍のtitleKanaが全て"titleKana"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("titleKana" + i, books.get(i).getTitleKana());
        }

        // 期待値: 返却された書籍のversionが全て"version"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("version" + i, books.get(i).getVersion());
        }

        // 期待値: 返却された書籍のseriesが全て"series"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("series" + i, books.get(i).getSeries());
        }

        // 期待値: 返却された書籍のauthorが全て"author"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("author" + i, books.get(i).getAuthor());
        }

        // 期待値: 返却された書籍のpublisherが全て"publisher"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("publisher" + i, books.get(i).getPublisher());
        }

        // 期待値: 返却された書籍のpublishYearが全て2000+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals(2000 + i, books.get(i).getPublishYear());
        }

        // 期待値: 返却された書籍のsizeInfoが全て"sizeInfo"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("sizeInfo" + i, books.get(i).getSizeInfo());
        }

        // 期待値: 返却された書籍のisbnが全て"isbn"+iであること
        for (int i = 0; i < 5; i++) {
            assertEquals("isbn" + i, books.get(i).getIsbn());
        }

        // 期待値: 返却された書籍のamountが全てiであること
        for (int i = 0; i < 5; i++) {
            assertEquals(i, books.get(i).getAmount());
        }

        // 期待値: 返却された書籍のcanRentが全てtrueであること
        for (int i = 0; i < 5; i++) {
            assertTrue(books.get(i).getCanRent());
        }


    }
}