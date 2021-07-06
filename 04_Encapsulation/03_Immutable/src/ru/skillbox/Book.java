package ru.skillbox;

public class Book {
    private final String name;
    private final String author;
    private final int numbOfPages;
    private final int ISBN;

    public Book(String name, String author, int numbOfPages, int isbn) {
        this.name = name;
        this.author = author;
        this.numbOfPages = numbOfPages;
        this.ISBN = isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumbOfPages() {
        return numbOfPages;
    }

    public int getISBN() {
        return ISBN;
    }
}
