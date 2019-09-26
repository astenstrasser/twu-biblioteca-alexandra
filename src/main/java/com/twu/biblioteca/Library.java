package com.twu.biblioteca;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> books;
    public Menu menu;

    public Library() {
        this.books = getAllBooks();
        this.menu = new Menu(this);
    }

    public void runLibrary(){
        welcomeMessage();
        this.menu.displayMenu();
    }

    public static void welcomeMessage() {
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }


    public static ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book("Slaughterhouse-Five", "Kurt Vonnegut", 1969));
        books.add(new Book("Fahrenheit 451", "Ray Bradbury", 1953));
        return books;
    }

    public void showAllBooks() {
        this.books.forEach(book -> System.out.println(book.getAuthor() + " - " + book.getTitle() + " (" + book.getPublicationYear() + ")"));
    }
}

