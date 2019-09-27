package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Book> books = new ArrayList<>();
    // TODO: library nao precisa saber que o menu existe
    Menu menu;

    public Library() {
        // TODO: remove
        this.menu = new Menu(this);
    }

    // TODO: remove
    public void runLibrary(){
        welcomeMessage();
        this.menu.displayMenu();
    }

    public static void welcomeMessage() {
        // TODO: remove
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    // TODO: remove
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

