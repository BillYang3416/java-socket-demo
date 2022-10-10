package model;

import java.io.Serializable;
import java.util.Arrays;

// must implement Serializable in order to be sent
public class Book implements Serializable {
    private String title;
    private String isbn;
    private long year;
    private String[] authors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public long getYear() {
        return year;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", year=" + year +
                ", authors=" + Arrays.toString(authors) +
                '}';
    }
}
