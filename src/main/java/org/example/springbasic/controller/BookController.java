package org.example.springbasic.controller;

import org.example.springbasic.controller.viewmodel.BookViewModel;
import org.example.springbasic.repository.entity.Book;
import org.example.springbasic.repository.jpa.BookRepository;
import org.example.springbasic.service.IBookService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

    @GetMapping("/")
    public List<BookViewModel> getBooks(Model model) {
        // 戻り値用のBookViewModelのリストを作成
        List<BookViewModel> books = new ArrayList<>();

        // bookServiceのgetBookListメソッドを呼び出してBookModelのリストを取得
        var bookModels = bookService.getBookList();

        // mapper
        ModelMapper modelMapper = new ModelMapper();

        bookModels.forEach(x -> {
            // mapperのmapメソッドを使ってBookModelをBookViewModelに変換
            BookViewModel book = modelMapper.map(x, BookViewModel.class);

            // BookViewModelをリストに追加
            books.add(book);
        });

        model.addAttribute("books", books);

        return books;
    }

//    @GetMapping("/title/{title}")
//    public List<Book> getBooksByTitle(@PathVariable String title){
//        return bookRepository.findByTitle(title);
//    }
//
//    @GetMapping("/{id}")
//    public Book getBookById(@PathVariable String bookId){
//        return bookRepository.findById(bookId)
//                .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "Book not found"));
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Book createBook(@RequestBody Book book){
//        return bookRepository.save(book);
//    }
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
