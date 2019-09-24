package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;



public class LibraryTest {

    @Test
    public void shouldShowWelcomeMessageWhenNewLibrary(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Library.welcomeMessage();
        assertThat(outContent.toString(), is(equalTo("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n")));
    }

    @Test
    public void menu_shouldShowListOfBooksWhenUserChooseOptionOne(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        int inputOption = 1;
        Library.menu(inputOption);

        assertThat(outContent.toString(), CoreMatchers.containsString("Menu of options"));
        assertThat(outContent.toString(), CoreMatchers.containsString("Kurt Vonnegut"));
        assertThat(outContent.toString(), CoreMatchers.containsString("Ray Bradbury"));
    }

    @Test
    public void shouldShowListOfAllBooks(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Library.showAllBooks();
        assertThat(outContent.toString(), CoreMatchers.containsString("Kurt Vonnegut"));
        assertThat(outContent.toString(), CoreMatchers.containsString("Ray Bradbury"));
    }

}
