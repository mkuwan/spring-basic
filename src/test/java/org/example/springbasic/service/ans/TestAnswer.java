package org.example.springbasic.service.ans;

import org.example.springbasic.repository.entity.Book;
import org.example.springbasic.repository.jpa.BookRepository;
import org.example.springbasic.service.BookService;
import org.example.springbasic.service.model.BookModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TestAnswer {

    @Mock
    private BookRepository bookRepositoryMock;


    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
    }

    /**
     * 正常系
     * BookService.getBook()のテスト
     * BookRepositoryはモック化せず、実際のインスタンスを使用する(実際のDBにアクセス)
     * 期待値: BookModelが返却されること
     * 期待値: BookModelのbookIdが"test-id-add002"であること
     * 期待値: BookModelのtitleが"テスト書籍Add002"であること
     * 期待値: BookModelのpublishYearが2022であること
     * 期待値: BookRepository.findById()が呼ばれたこと
     * 期待値: BookRepository.findById()が1回だけ呼ばれたこと
     */
    @Test
    void getBook_success() {
        // arrange
        // BookModelのリストを作成
        List<Book> books = new ArrayList<>();
        // BookModelのリストに1個のBookModelを追加
        Book book = new Book();
        // bookIdにUUIDを設定
        book.setBookId("test-id-add002");
        // titleに"テストタイトル"を設定
        book.setTitle("テスト書籍Add002");
        // publishYearに2018を設定
        book.setPublishYear(2022);
        // BookModelのリストに追加
        books.add(book);
        bookRepository.saveAll(books);

        // BookServiceのインスタンスを作成
        BookService bookService = new BookService(bookRepository);

        // act
        // BookService.getBook()を呼び出し
        var result = bookService.getBook("test-id-add002");

        // assert
        // 結果がnullでないこと
        assertNotNull(result);
        // BookModelが返却されること
        assertTrue(result instanceof BookModel);
        // BookModelのbookIdが"test-id-add002"であること
        assertEquals("test-id-add002", result.getBookId());
        // BookModelのtitleが"テスト書籍Add002"であること
        assertEquals("テスト書籍Add002", result.getTitle());
        // BookModelのpublishYearが2022であること
        assertEquals(2022, result.getPublishYear());

//        // BookRepository.findById()が呼ばれたこと
//        verify(bookRepository).findById("test-id-add002");
//        // BookRepository.findById()が1回だけ呼ばれたこと
//        verify(bookRepository, times(1)).findById("test-id-add002");

    }


    /**
     * 正常系
     * BookService.addBook()のテスト
     * 既存のデータなし
     * 期待値: BookModelが返却されること
     * 期待値: BookModelのbookIdが"test-id-add002"であること
     * 期待値: BookModelのtitleが"テスト書籍Add002"であること
     * 期待値: BookModelのpublishYearが2022であること
     * 期待値: BookRepository.findById()が呼ばれたこと
     * 期待値: BookRepository.findById()が1回だけ呼ばれたこと
     * 期待値: BookRepository.save()が呼ばれたこと
     * 期待値: BookRepository.save()が1回だけ呼ばれたこと
     * 期待値: DBに登録されたBookModelのbookIdが"test-id-add002"であること
     * 期待値: DBに登録されたBookModelのtitleが"テスト書籍Add002"であること
     * 期待値: DBに登録されたBookModelのpublishYearが2022であること
     */
    @Test
    void addBook_success() throws Exception {
        // arrange
        // BookServiceのインスタンスを作成
        BookService bookService = new BookService(bookRepository);

        // 追加するBookModelを作成
        BookModel bookModel = new BookModel();
        bookModel.setBookId("test-id-add002");
        bookModel.setTitle("テスト書籍Add002");
        bookModel.setPublishYear(2022);

        // act
        // BookService.addBook()を呼び出し
        var result = bookService.addBook(bookModel);

        // assert
        // BookModelが返却されること
        assertNotNull(result);
        // BookModelのbookIdが"test-id-add002"であること
        assertEquals("test-id-add002", result.getBookId());
        // BookModelのtitleが"テスト書籍Add002"であること
        assertEquals("テスト書籍Add002", result.getTitle());
        // BookModelのpublishYearが2022であること
        assertEquals(2022, result.getPublishYear());
//        // BookRepository.findById()が呼ばれたこと
//        verify(bookRepository).findById("test-id-add002");
//        // BookRepository.findById()が1回だけ呼ばれたこと
//        verify(bookRepository, times(1)).findById("test-id-add002");
//        // BookRepository.save()が呼ばれたこと
//        verify(bookRepository).save(any(Book.class));
//        // BookRepository.save()が1回だけ呼ばれたこと
//        verify(bookRepository, times(1)).save(any(Book.class));

        var record = bookRepository.findById("test-id-add002");
        // DBに登録されたBookModelのbookIdが"test-id-add002"であること
        assertEquals("test-id-add002", record.get().getBookId());
        // DBに登録されたBookModelのtitleが"テスト書籍Add002"であること
        assertEquals("テスト書籍Add002", record.get().getTitle());
        // DBに登録されたBookModelのpublishYearが2022であること
        assertEquals(2022, record.get().getPublishYear());

    }


    /**
     * 異常系
     * BookService.addBook()のテスト
     * BookRepositoryはモック化せず、実際のインスタンスを使用する(実際のDBにアクセス)
     * 既存のbookIdを持つBookModelを追加しようとした場合
     * 期待値: 例外が発生すること
     * 期待値: 例外のメッセージが"bookId is already exists"であること
     //     * 以下はモックを使わないとテストできないためコメントアウト
     //     * 期待値: BookRepository.findById()が呼ばれたこと
     //     * 期待値: BookRepository.findById()が1回だけ呼ばれたこと
     //     * 期待値: BookRepository.save()が呼ばれないこと
     //     * 期待値: BookRepository.save()が0回呼ばれたこと
     */
    @Test
    void addBook_existedBookId_returnException() {
        // arrange
        // BookModelのリストを作成
        List<Book> books = new ArrayList<>();
        // BookModelのリストに1個のBookModelを追加
        Book book = new Book();
        // bookIdにUUIDを設定
        book.setBookId("test-id-add001");
        // titleに"テストタイトル"を設定
        book.setTitle("テスト書籍Add001");
        // publishYearに2013を設定
        book.setPublishYear(2013);
        // BookModelのリストに追加
        books.add(book);
        bookRepository.saveAll(books);

        // BookServiceのインスタンスを作成
        BookService bookService = new BookService(bookRepository);

        // act
        // 同じbookIdを持つBookModelを追加しようとした場合
        BookModel bookModel = new BookModel();
        bookModel.setBookId("test-id-add001");
        bookModel.setTitle("テスト書籍Add001");
        bookModel.setPublishYear(2013);
        // 例外が発生すること
        Exception exception = assertThrows(Exception.class, () -> {
            // BookService.addBook()を呼び出し
            bookService.addBook(bookModel);
        });

        // assert
        // 例外のメッセージが"bookId is already exists"であること
        assertEquals("bookId is already exists", exception.getMessage());
//        // BookRepository.findById()が呼ばれたこと
//        verify(bookRepository, times(1)).findById(book.getBookId());
//        // BookRepository.findById()が1回だけ呼ばれたこと
//        verify(bookRepository, times(1)).findById(book.getBookId());
//        // BookRepository.save()が呼ばれないこと
//        verify(bookRepository, never()).save(book);
//        // BookRepository.save()が0回呼ばれたこと
//        verify(bookRepository, times(0)).save(book);
    }
}
