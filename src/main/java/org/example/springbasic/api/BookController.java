package org.example.springbasic.api;

import org.example.springbasic.repository.entity.Book;
import org.example.springbasic.repository.jpa.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().forEach(books::add);
        return books;

    }

    @GetMapping("/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title){
        return bookRepository.findByTitle(title);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "Book not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id){
        bookRepository.findById(id)
                        .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "Book not found"));
        bookRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book){
        if(!book.getBookId().equals(id)){
            throw new HttpClientErrorException(HttpStatusCode.valueOf(400), "Id in the path and body are different");
        }
        bookRepository.findById(id)
                        .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "Book not found"));
        return bookRepository.save(book);
    }
}
