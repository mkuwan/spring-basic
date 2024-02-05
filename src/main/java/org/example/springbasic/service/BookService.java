package org.example.springbasic.service;

import org.example.springbasic.repository.jpa.BookRepository;
import org.example.springbasic.service.model.BookModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService implements IBookService{

    private final BookRepository bookRepository;

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookModel> getBookList() {
        logger.info("getBookListが呼ばれました");
        List<BookModel> books = new ArrayList<>();
        bookRepository.findAll()
                .forEach(x -> {
                    logger.info("BookModel: " + x.getTitle());
                    BookModel bookModel = new BookModel(x.getBookId(),
                            x.getTitle(),
                            x.getTitleKana(),
                            x.getVersion(),
                            x.getSeries(),
                            x.getAuthor(),
                            x.getPublisher(),
                            x.getPublishYear(),
                            x.getSizeInfo(),
                            x.getIsbn(),
                            x.getAmount());
                    books.add(bookModel);
                });
        return books;
    }

    @Override
    public BookModel getBook(String bookId) {
        logger.info("getBookが呼ばれました");
        return null;
    }

    @Override
    public void addBook(BookModel bookModel) {
        logger.info("addBookが呼ばれました");
    }

    @Override
    public void deleteBook(String bookId) {
        logger.info("deleteBookが呼ばれました");
    }

    @Override
    public void updateBook(String bookId, BookModel bookModel) {
        logger.info("updateBookが呼ばれました");
    }

    @Override
    public List<BookModel> getBookByTitle(String title, String titleKana) {
        logger.info("getBookByTitleが呼ばれました");
        return null;
    }

    @Override
    public List<BookModel> getBookByAuthor(String author) {
        logger.info("getBookByAuthorが呼ばれました");
        return null;
    }

    @Override
    public List<BookModel> getBookByPublisher(String publisher) {
        logger.info("getBookByPublisherが呼ばれました");
        return null;
    }

    @Override
    public List<BookModel> getBookByPublishYear(Integer publishYear) {
        logger.info("getBookByPublishYearが呼ばれました");
        return null;
    }

    @Override
    public BookModel getBookByIsbn(String isbn) {
        logger.info("getBookByIsbnが呼ばれました");
        return null;
    }
}
