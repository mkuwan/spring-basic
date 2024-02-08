package org.example.springbasic.repository.jpa;

import org.example.springbasic.repository.entity.Book;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {
    Book getBookByBookId(String bookId);


    // SQLを直接記述することも可能
     @Query("SELECT b FROM Book b WHERE b.title LIKE %:title%")
     List<Book> findBooksByTitle(@Param("title") String title);
}
