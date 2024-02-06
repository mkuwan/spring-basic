package org.example.springbasic.controller;

import org.example.springbasic.controller.viewmodel.BookViewModel;
import org.example.springbasic.repository.entity.Book;
import org.example.springbasic.repository.jpa.BookRepository;
import org.example.springbasic.service.IBookService;
import org.example.springbasic.service.model.BookModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity getBooks(Model model) {
        // logger
        logger.info("BookController getBooksが呼ばれました");

        // 戻り値用のBookViewModelのリストを作成
        List<BookViewModel> books = new ArrayList<>();

        // bookServiceのgetBookListメソッドを呼び出してBookModelのリストを取得
        logger.info("bookService.getBookList()が呼ばれました");
        var bookModels = bookService.getBookList();

        // mapper
        ModelMapper modelMapper = new ModelMapper();

        logger.info("bookModels.size(): " + bookModels.size());
        bookModels.forEach(x -> {
            // mapperのmapメソッドを使ってBookModelをBookViewModelに変換
            BookViewModel book = modelMapper.map(x, BookViewModel.class);

            logger.info("BookViewModel: " + book.getTitle());
            // BookViewModelをリストに追加
            books.add(book);
        });

//        model.addAttribute("books", books);
        logger.info("books.size(): " + books.size());
        return new ResponseEntity<>(books, HttpStatus.OK);
    }



    @GetMapping("/{bookId}")
    public ResponseEntity getBook(@PathVariable String bookId){
        // logger
        logger.info("BookController getBookが呼ばれました");

        try {
            logger.info("bookId: " + bookId);
            var model = bookService.getBook(bookId);

            if (model == null) {
//            throw new HttpClientErrorException(HttpStatusCode.valueOf(404), "Book not found");
                logger.error("Book not found");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("本が見つかりませんでした");
            }

            // mapper
            ModelMapper modelMapper = new ModelMapper();
            var viewModel = modelMapper.map(model, BookViewModel.class);

            logger.info("BookModel: " + viewModel.getTitle());
            return new ResponseEntity(viewModel, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity addBook(@RequestBody BookViewModel book){
        // logger
        logger.info("BookController createBookが呼ばれました");

        // mapper
        ModelMapper modelMapper = new ModelMapper();
        var bookModel = modelMapper.map(book, BookModel.class);

        try {
            // bookServiceのaddBookメソッドを呼び出してBookModelを登録
            logger.info("bookService.addBookが呼ばれました");
            var model = bookService.addBook(bookModel);

            // mapperのmapメソッドを使ってBookModelをBookViewModelに変換
            logger.info("mapperのmapメソッドを使ってBookModelをBookViewModelに変換");
            var viewModel = modelMapper.map(model, BookViewModel.class);

            logger.info("bookService.addBookが正常に終了しました");
            return new ResponseEntity(viewModel, HttpStatus.CREATED);

        } catch (Exception e) {
            logger.error("BookService addBookがエラーを返しました");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
//
//    @DeleteMapping("/{id}")
//    public void deleteBook(@PathVariable String bookId){
//        bookRepository.findById(bookId)
//                        .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "Book not found"));
//        bookRepository.deleteById(bookId);
//    }
//
//    @PutMapping("/{id}")
//    public Book updateBook(@PathVariable String bookId, @RequestBody Book book){
//        if(!book.getBookId().equals(bookId)){
//            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), "Id in the path and body are different");
//        }
//        bookRepository.findById(bookId)
//                        .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "Book not found"));
//        return bookRepository.save(book);
//    }
}
