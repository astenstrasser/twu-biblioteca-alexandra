package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;


public class LibraryTest {
    private static final Book BOOK_ONE = new Book("Fahrenheit 451", "Ray Bradbury", 1953);
    private static final Book BOOK_TWO = new Book("Slaughterhouse-Five", "Kurt Vonnegut", 1969);;

    @Test
    public void shouldShowWelcomeMessageWhenNewLibrary() {
        // given
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // when
        Library.welcomeMessage();

        // then
        assertThat(outContent.toString()).isEqualTo("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n");
    }

    @Test
    public void AddBookToLibrary(){
        // given
        Library library = new Library();

        // when
        library.addBook(BOOK_ONE);

        // then
        assertThat(library.getBooks()).containsOnly(BOOK_ONE);
    }

    @Test
    public void shouldShowListOfAllBooks() {
        // given
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Library library = new Library();

        // when
        library.addBook(BOOK_ONE);
        library.addBook(BOOK_TWO);
        library.showAllBooks();

        // then
        assertThat(outContent.toString()).contains("Kurt Vonnegut");
        assertThat(outContent.toString()).contains("Ray Bradbury");
        assertThat(library.getBooks().size()).isEqualTo(2);
    }


}
