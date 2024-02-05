package org.example.springbasic.service;

import org.example.springbasic.service.model.BookModel;

import java.util.List;


public interface IBookService {

    List<BookModel> getBookList();

    BookModel getBook(String bookId);

    void addBook(BookModel bookModel);

    void deleteBook(String bookId);

    void updateBook(String bookId, BookModel bookModel);

    List<BookModel> getBookByTitle(String title, String titleKana);

    List<BookModel> getBookByAuthor(String author);

    List<BookModel> getBookByPublisher(String publisher);

    List<BookModel> getBookByPublishYear(Integer publishYear);

    BookModel getBookByIsbn(String isbn);


}
