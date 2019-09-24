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
        Book.viewAllBooks();
        assertThat(outContent.toString(), is(equalTo("Kurt Vonnegut - Slaughterhouse-Five (1969)\nRay Bradbury - Fahrenheit 451 (1953)\n")));
    }

}
