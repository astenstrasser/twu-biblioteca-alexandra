package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static void main(String[] args) {


        Library library = new Library();

        library.addBook(new Book("Fahrenheit 451", "Ray Bradbury", 1953));
        library.addBook(new Book("Slaughterhouse-Five", "Kurt Vonnegut", 1969));

        library.runLibrary();
    }

}
