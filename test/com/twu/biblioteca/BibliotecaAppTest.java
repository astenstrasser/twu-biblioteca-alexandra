package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;



public class BibliotecaAppTest {

    @Test
    public void shouldShowWelcomeMessage(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        BibliotecaApp.welcomeMessage();
        assertThat(outContent.toString(), is(equalTo("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n")));
    }

    @Test
    public void menu_shouldShowListOfBooksWhenOptionOne(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        int inputOption = 1;
        BibliotecaApp.menu(inputOption);

        assertThat(outContent.toString(), CoreMatchers.containsString("Menu of options"));
        assertThat(outContent.toString(), CoreMatchers.containsString("Kurt Vonnegut"));
        assertThat(outContent.toString(), CoreMatchers.containsString("Ray Bradbury"));
    }

    @Test
    public void shouldShowListOfAllBooks(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Book.viewAllBooks();
        assertThat(outContent.toString(), CoreMatchers.containsString("Kurt Vonnegut"));
        assertThat(outContent.toString(), CoreMatchers.containsString("Ray Bradbury"));
    }

}
