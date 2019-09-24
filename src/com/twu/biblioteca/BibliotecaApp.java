package com.twu.biblioteca;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        welcomeMessage();
        viewAllBooks();
    }


    public static void welcomeMessage(){
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    public static ArrayList<String> getAllBooks() {
        ArrayList<String> allBooks = new ArrayList<>();
        allBooks.add("Book 1");
        allBooks.add("Book 2");
        allBooks.add("Book 3");
        return allBooks;
    }

    public static void viewAllBooks(){
        ArrayList<String> books = getAllBooks();
        books.forEach(book -> System.out.println(book));
    }
}
