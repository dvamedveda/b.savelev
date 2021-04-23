package ru.job4j.manytomany;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс сущности Книга.
 */
@Entity
@Table(name = "books")
public class Book {

    /**
     * Идентификатор.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Название книги.
     */
    @Column
    private String title;

    /**
     * Год выпуска.
     */
    @Column
    private int year;

    public Book() {

    }

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return id == book.id
                && title.equals(book.title)
                && year == book.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, year);
    }
}