package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books = new ArrayList<>();
    Menu menu;

    public Library() {
        this.menu = new Menu(this);
    }

    public void runLibrary(){
        welcomeMessage();
        this.menu.displayMenu();
    }

    public static void welcomeMessage() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public void showAllBooks() {
        this.books.forEach(book -> System.out.println(book.getAuthor() + " - " + book.getTitle() + " (" + book.getPublicationYear() + ")"));
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public List<Book> getBooks() {
        return this.books;
    }

}

