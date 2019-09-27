package com.twu.biblioteca;

import junit.extensions.TestSetup;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;


public class LibraryTest {

    Library library;
    Book bookOne;
    Book bookTwo;

    @Before
    public void TestSetup(){
        this.library = new Library();
        this.bookOne = new Book("Fahrenheit 451", "Ray Bradbury", 1953);
        this.bookTwo = new Book("Slaughterhouse-Five\n", "Kurt Vonnegut", 1969);
    }

    @Test
    public void shouldShowWelcomeMessageWhenNewLibrary() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Library.welcomeMessage();
        assertThat(outContent.toString()).isEqualTo("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
    }

    @Test
    public void AddBookToLibrary(){

        this.library.addBook(bookOne);

        assertThat(library.getBooks()).containsOnly(bookOne);
    }

    @Test
    public void shouldShowListOfAllBooks() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        library.addBook(bookOne);
        library.addBook(bookTwo);
        library.showAllBooks();

        assertThat(outContent.toString()).contains("Kurt Vonnegut");
        assertThat(outContent.toString()).contains("Ray Bradbury");
        assertThat(library.getBooks().size()).isEqualTo(2);
    }


}
