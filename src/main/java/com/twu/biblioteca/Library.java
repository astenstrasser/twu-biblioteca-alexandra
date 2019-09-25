package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {

    public Library() {
        welcomeMessage();
        Menu menu = new Menu();
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

    public static void showAllBooks() {
        ArrayList<Book> books = getAllBooks();
        books.forEach(book -> System.out.println(book.getAuthor() + " - " + book.getTitle() + " (" + book.getPublicationYear() + ")"));
    }
}

