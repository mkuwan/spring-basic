package org.example.springbasic.service;

import org.example.springbasic.repository.entity.Book;
import org.example.springbasic.repository.jpa.BookRepository;
import org.example.springbasic.service.model.BookModel;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@Service
public class BookService implements IBookService{

    private final BookRepository bookRepository;

    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookModel> getBookList() {
        logger.info("BookService getBookListが呼ばれました");

        List<BookModel> books = new ArrayList<>();
        // mapper
        ModelMapper modelMapper = new ModelMapper();

        bookRepository.findAll()
                .forEach(x -> {
                    logger.info("BookModel: " + x.getTitle());
                    BookModel bookModel = modelMapper.map(x, BookModel.class);

                    // 以下のコードは、ModelMapperを使わない場合のコード
//                    BookModel bookModel = new BookModel(x.getBookId(),
//                            x.getTitle(),
//                            x.getTitleKana(),
//                            x.getVersion(),
//                            x.getSeries(),
//                            x.getAuthor(),
//                            x.getPublisher(),
//                            x.getPublishYear(),
//                            x.getSizeInfo(),
//                            x.getIsbn(),
//                            x.getAmount(),
//                            x.getCanRent());

                    books.add(bookModel);
                });
        return books;
    }

    @Override
    public BookModel getBook(String bookId) {
        logger.info("BookService getBookが呼ばれました");

        BookModel bookModel = new BookModel();

        // mapper
        ModelMapper modelMapper = new ModelMapper();

        var book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            bookModel = modelMapper.map(book.get(), BookModel.class);
        } else{
            bookModel = null;
        }
        return bookModel;
    }

    @Transactional
    @Override
    public BookModel addBook(BookModel bookModel) throws Exception {
        logger.info("BookService addBookが呼ばれました");

        if (bookModel.getBookId() != null && !bookModel.getBookId().isBlank()) {
            var existingBook = bookRepository.findById(bookModel.getBookId());
            if (existingBook.isPresent()) {
                // すでに蔵書がある場合は新規登録はできない
                throw new Exception("bookId is already exists");
            }
        } else {
            bookModel.setBookId(UUID.randomUUID().toString());
            logger.info("bookIdをUUIDで生成 bookId: " + bookModel.getBookId());
        }

        // mapper
        ModelMapper modelMapper = new ModelMapper();

        // BookModelをBookに変換
        logger.info("BookModelをBookに変換");
        var book = modelMapper.map(bookModel, Book.class);

        // DBに登録
        logger.info("bookRepository.save(book)が呼ばれました");
        bookRepository.save(book);
        return bookModel;
    }

    @Transactional
    @Override
    public void deleteBook(String bookId) throws Exception {
        logger.info("BookService deleteBookが呼ばれました");

        if (bookId == null || bookId.isBlank()) {
            throw new Exception("bookId is null or empty");
        }

        // 削除の場合は、蔵書をすべて処分として複数冊あっても０にする
        var existedBook = bookRepository.findById(bookId);
        if (existedBook.isPresent()){
            // 物理削除の場合
//            bookRepository.deleteById(bookId);

            // すでに０冊のものを削除処理することはできない
            if (existedBook.get().getAmount() == 0) {
                throw new Exception("book's amount is already 0");
            }

            // amountを0にする
            existedBook.get().setAmount(0);
            bookRepository.save(existedBook.get());

        } else {
            throw new Exception("book is not exists");
        }
    }

    @Transactional
    @Override
    public BookModel updateBook(String bookId, BookModel bookModel) throws Exception {
        logger.info("BookService updateBookが呼ばれました");

        if (bookId == null || bookId.isBlank()) {
            throw new Exception("bookId is null or empty");
        }

        if (!bookModel.getBookId().equals(bookId)) {
            throw new Exception("bookId is not match");
        }

        var existedBook = bookRepository.findById(bookId);
        if (existedBook.isPresent()){
            // mapper
            ModelMapper modelMapper = new ModelMapper();

            // BookModelをBookに変換
            logger.info("BookModelをBookに変換");
            var book = modelMapper.map(bookModel, Book.class);

            bookRepository.save(book);
        } else {
            throw new Exception("bookId is not exists");
        }

        return bookModel;
    }

}
