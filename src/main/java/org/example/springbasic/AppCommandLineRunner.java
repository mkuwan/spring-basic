package org.example.springbasic;

import org.example.springbasic.repository.entity.Book;
import org.example.springbasic.repository.jpa.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppCommandLineRunner.class);
    private final BookRepository bookRepository;

    public AppCommandLineRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    @Override
    public void run(String... args) throws Exception {
        logger.info("Start CommandLineRunner");

        try {
            // サンプルデータ作成
            for (int i = 0; i < 20; i++) {
                Book book = new Book();
                book.setBookId(UUID.randomUUID().toString());
                book.setTitle("title" + (i + 1));
                book.setAuthor("author" + (i + 1));
                bookRepository.save(book);
            }
        } catch (Exception e) {
            logger.error("Error in CommandLineRunner", e);
        }
    }
}
