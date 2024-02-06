package org.example.springbasic.service;

import org.example.springbasic.service.model.BookModel;

import java.util.List;


public interface IBookService {

    List<BookModel> getBookList();

    BookModel getBook(String bookId);

    BookModel addBook(BookModel bookModel) throws Exception;

    void deleteBook(String bookId) throws Exception;

    BookModel updateBook(String bookId, BookModel bookModel) throws Exception;

//    List<BookModel> getBookByTitle(String title, String titleKana);
//
//    List<BookModel> getBookByAuthor(String author);
//
//    List<BookModel> getBookByPublisher(String publisher);
//
//    List<BookModel> getBookByPublishYear(Integer publishYear);
//
//    BookModel getBookByIsbn(String isbn);


}
