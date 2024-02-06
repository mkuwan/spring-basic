package org.example.springbasic.service.model;

import java.io.Serializable;

public class BookModel {

    /** Fields */

    /**
     * タイトル
     * The title of the book.
     */
    private String bookId;

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

    /**
     * 貸出可否
     * The book can be rented or not.
     */
    private Boolean canRent = false;

    /** No-args Constructor */
    public BookModel() {
    }

    /** Full-args Constructor */
    public BookModel(String bookId, String title, String titleKana,
                     String version, String series, String author,
                     String publisher, Integer publishYear,
                     String sizeInfo, String isbn, Integer amount, Boolean canRent) {
        this.bookId = bookId;
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
        this.canRent = canRent;
    }


    /** Getter methods */

    public String getBookId() {
        return bookId;
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

    public Boolean getCanRent() {
        return canRent;
    }


    /** Setter methods */

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitleKana(String titleKana) {
        this.titleKana = titleKana;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public void setSizeInfo(String sizeInfo) {
        this.sizeInfo = sizeInfo;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setCanRent(Boolean canRent) {
        this.canRent = canRent;
    }
}
