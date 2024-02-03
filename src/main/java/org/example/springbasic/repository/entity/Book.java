package org.example.springbasic.repository.entity;

import jakarta.persistence.*;

/**
 * The entity for the book.
 */
@Entity
public class Book {

    /**
     * The id of the book.
     * Id is auto generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The title of the book.
     */
    @Column(nullable = false)
    private String title;

    /**
     * The author of the book.
     */
    @Column(nullable = false)
    private String author;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
}
