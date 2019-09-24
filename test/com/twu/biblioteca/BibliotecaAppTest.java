package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;



public class BibliotecaAppTest {

    @Test
    public void welcomeTest(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        BibliotecaApp.welcomeMessage();
        assertThat(outContent.toString(), is(equalTo("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!\n")));
    }

    @Test
    public void viewAllBooks(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        BibliotecaApp.viewAllBooks();
        assertThat(outContent.toString(), is(equalTo("Book 1\nBook 2\nBook 3\n")));
    }

}
