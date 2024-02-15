package org.example.springbasic.service;

import org.example.springbasic.repository.entity.Book;
import org.example.springbasic.repository.jpa.BookRepository;
import org.example.springbasic.service.model.BookModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


//@ContextConfiguration(classes = {BookService.class, BookRepository.class})  // このクラスのテストで使用するコンポーネントを指定
@SpringBootTest // Spring Bootのアプリケーションコンテキストをロード
class BookServiceTest {

//        Assertions.assertThrows(Exception.class, () -> {
//            throw new Exception("test");
//        });


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
        verify(bookRepositoryMock).findAll();

        // BookRepository.findAll()が1回だけ呼ばれたこと
        verify(bookRepositoryMock, times(1)).findAll();
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
        // 結果がnullでないこと
        assertNotNull(result);
        // List<BookModel>が返却されること
        assertTrue(result instanceof List);

        // BookModelのリストの要素数が5であること
        assertEquals(5, result.size());
        // BookModelのリストの各要素のtitleが"テストタイトル" + iであること
        for (int i = 0; i < result.size(); i++) {
            assertEquals("テストタイトル" + i, result.get(i).getTitle());
        }


        // verifyはmockを使わない場合は、呼ばれた回数を検証することができないためエラーになる
        // assertThrowsで例外をキャッチする使い方は、以下のようになる
        Assertions.assertThrows(Exception.class, () -> {
            // BookRepository.findAll()が呼ばれたこと
            verify(bookRepository, times(1)).findAll();
        });

        var ex = Assertions.assertThrows(Exception.class, () -> {
            // BookRepository.findAll()が呼ばれたこと
            verify(bookRepository).findAll();
            // BookRepository.findAll()が1回だけ呼ばれたこと
            verify(bookRepository, times(1)).findAll();
        });
        System.out.println(ex.getMessage());


        try {
            // BookRepository.findAll()が呼ばれたこと
            verify(bookRepository, times(1)).findAll();
        } catch (Exception e) {
            // printStackTraceとSystem.out.println(e.getMessage())の違い
            // printStackTraceはエラーの詳細を出力する：エラーの原因や発生箇所など
            // System.out.println(e.getMessage())はエラーのメッセージのみを出力する


            System.out.println("e.printStackTrace()の場合の出力結果");
            e.printStackTrace();


            System.out.println("System.out.printlnの場合の出力結果");
            System.out.println(e.getMessage());
            // 以下のエラーが出力される
//            Argument passed to verify() is of type $Proxy114 and is not a mock!
//            Make sure you place the parenthesis correctly!
//            See the examples of correct verifications:
//              verify(mock).someMethod();
//              verify(mock, times(10)).someMethod();
//              verify(mock, atLeastOnce()).someMethod();
        }

    }


    /**
     * 正常系
     * BookService.getBook()のテスト
     * BookRepository.findById()のモックを作成し、Book(Entity)を返却する
     * 期待値: BookModelが返却されること
     * 期待値: BookModelのtitleが"ゲットタイトル"であること
     * 期待値: BookRepository.findById()が1回だけ呼ばれたこと
     */
    @Test
    void getBook_with_mock_success() {
        // arrange
        // Book(Entity)を作成
        Book book = new Book();
        // bookIdにUUIDを設定
        book.setBookId("Id001");
        // titleに"ゲットタイトル"を設定
        book.setTitle("ゲットタイトル");

        // BookRepository.findById()のモックを作成
        // BookRepository.findById()が呼ばれたら、bookを返却する
        when(bookRepositoryMock.findById("Id001"))
                .thenReturn(java.util.Optional.of(book));

        // BookServiceのインスタンスを作成
        BookService bookService = new BookService(bookRepositoryMock);

        // act
        // BookService.getBook()を呼び出し
        var result = bookService.getBook("Id001");

        // assert
        // BookModelが返却されること
        assertNotNull(result);
        // BookModelのtitleが"ゲットタイトル"であること
        assertEquals("ゲットタイトル", result.getTitle());


//        // BookRepository.findById()が1回だけ呼ばれたこと
//        verify(bookRepositoryMock, times(1)).findById(book.getBookId());

    }

    /**
     * 正常系
     * BookService.addBook()のテスト
     * 既存のデータなし
     * 期待値: BookModelが返却されること
     * 期待値: 返却されたBookModelのbookIdが"test-id-add002"であること
     * 期待値: 返却されたBookModelのtitleが"テスト書籍Add002"であること
     * 期待値: 返却されたBookModelのpublishYearが2022であること
     * 期待値: DBに登録されたBookModelのbookIdが"test-id-add002"であること
     * 期待値: DBに登録されたBookModelのtitleが"テスト書籍Add002"であること
     * 期待値: DBに登録されたBookModelのpublishYearが2022であること
//     * 以下はモックを使わないとテストできない
//     * 期待値: BookRepository.findById()が呼ばれたこと
//     * 期待値: BookRepository.findById()が1回だけ呼ばれたこと
//     * 期待値: BookRepository.save()が呼ばれたこと
//     * 期待値: BookRepository.save()が1回だけ呼ばれたこと
     */
    @Test
    void addBook_success_by_yamaguchi() throws Exception {

    }


    /**
     * 異常系
     * BookService.addBook()のテスト
     * 既存のbookIdを持つBookModelを追加しようとした場合
     * 期待値: 例外が発生すること
     * 期待値: 例外のメッセージが"bookId is already exists"であること
//     * 以下はモックを使わないとテストできない
//     * 期待値: BookRepository.findById()が呼ばれたこと
//     * 期待値: BookRepository.findById()が1回だけ呼ばれたこと
//     * 期待値: BookRepository.save()が呼ばれないこと
//     * 期待値: BookRepository.save()が0回呼ばれたこと
     */
    @Test
    void addBook_existedBookId_returnException_by_yamaguchi() {

    }


    /**
     * 正常系
     * BookService.addBook()のテスト
     * 既存のデータなし
     * 期待値: BookModelが返却されること
     * 期待値: 返却されたBookModelのbookIdが"test-id-add002"であること
     * 期待値: 返却されたBookModelのtitleが"テスト書籍Add002"であること
     * 期待値: 返却されたBookModelのpublishYearが2022であること
     * 期待値: DBに登録されたBookModelのbookIdが"test-id-add002"であること
     * 期待値: DBに登録されたBookModelのtitleが"テスト書籍Add002"であること
     * 期待値: DBに登録されたBookModelのpublishYearが2022であること
     //     * 以下はモックを使わないとテストできない
     //     * 期待値: BookRepository.findById()が呼ばれたこと
     //     * 期待値: BookRepository.findById()が1回だけ呼ばれたこと
     //     * 期待値: BookRepository.save()が呼ばれたこと
     //     * 期待値: BookRepository.save()が1回だけ呼ばれたこと
     */
    @Test
    void addBook_success_by_nakano() throws Exception {

    }


    /**
     * 異常系
     * BookService.addBook()のテスト
     * 既存のbookIdを持つBookModelを追加しようとした場合
     * 期待値: 例外が発生すること
     * 期待値: 例外のメッセージが"bookId is already exists"であること
     //     * 以下はモックを使わないとテストできない
     //     * 期待値: BookRepository.findById()が呼ばれたこと
     //     * 期待値: BookRepository.findById()が1回だけ呼ばれたこと
     //     * 期待値: BookRepository.save()が呼ばれないこと
     //     * 期待値: BookRepository.save()が0回呼ばれたこと
     */
    @Test
    void addBook_existedBookId_returnException_by_nakano() {

    }




    @Test
    void deleteBook_with_repository_success() {
    }

    @Test
    void updateBook() {
    }
}