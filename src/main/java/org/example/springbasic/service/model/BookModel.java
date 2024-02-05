package org.example.springbasic.service.model;

import jakarta.persistence.Column;

public class BookModel {
    private String BookId;

    /**
     * タイトル
     * The title of the book.
     */
    private String title;

    /**
     * タイトルよみ
     * The title of the book in Kana.
     */
    private String titleKana;

    /**
     * 版
     * The version of the book.
     */
    private String version;

    /**
     * シリーズ
     * The series of the book.
     */
    private String series;

    /**
     * 責任表示：著者や編集者等
     * The author of the book.
     */
    private String author;

    /**
     * 出版者
     * The publisher of the book.
     */
    private String publisher;

    /**
     * 出版年
     * The year of the book published.
     */
    private Integer publishYear;

    /**
     * サイズ、容量等
     * The size of the book.
     */
    private String sizeInfo;

    /**
     * ISBN
     * The ISBN of the book.
     */
    private String isbn;

    /**
     * 蔵書数
     * The amount of the book.
     */
    private Integer amount;

    public BookModel(String bookId, String title, String titleKana,
                     String version, String series,
                     String author,
                     String publisher, Integer publishYear,
                     String sizeInfo, String isbn, Integer amount) {
        BookId = bookId;
        this.title = title;
        this.titleKana = titleKana;
        this.version = version;
        this.series = series;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.sizeInfo = sizeInfo;
        this.isbn = isbn;
        this.amount = amount;
    }

    public String getBookId() {
        return BookId;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleKana() {
        return titleKana;
    }

    public String getVersion() {
        return version;
    }

    public String getSeries() {
        return series;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public String getSizeInfo() {
        return sizeInfo;
    }

    public String getIsbn() {
        return isbn;
    }

    public Integer getAmount() {
        return amount;
    }
}
