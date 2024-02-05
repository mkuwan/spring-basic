package org.example.springbasic.controller.viewmodel;

import jakarta.persistence.Column;

import java.io.Serializable;

/**
 * BookViewModel(表示用ビューモデル)クラス
 */
public class BookViewModel implements Serializable {
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

    public String getBookId() {
        return BookId;
    }

    public void setBookId(String bookId) {
        BookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleKana() {
        return titleKana;
    }

    public void setTitleKana(String titleKana) {
        this.titleKana = titleKana;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public String getSizeInfo() {
        return sizeInfo;
    }

    public void setSizeInfo(String sizeInfo) {
        this.sizeInfo = sizeInfo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
