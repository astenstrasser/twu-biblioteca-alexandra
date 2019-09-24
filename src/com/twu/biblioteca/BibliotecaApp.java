package com.twu.biblioteca;

import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {
        welcomeMessage();
        Book.viewAllBooks();
    }

    public static void welcomeMessage(){
        System.out.println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

}
