package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MenuTest {

    @Test
    public void menu_shouldShowListOfBooksWhenUserChooseOptionOne(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        int inputOption = 1;
        Menu.redirectToUsersOption(inputOption);

        assertThat(outContent.toString(), CoreMatchers.containsString("Kurt Vonnegut"));
        assertThat(outContent.toString(), CoreMatchers.containsString("Ray Bradbury"));
    }


    @Test
    public void menu_shouldNotifyWhenUserChooseInvalidOption(){
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        int inputOption = 3;
        Menu.redirectToUsersOption(inputOption);
        assertThat(outContent.toString(), CoreMatchers.containsString("Please select a valid option"));
    }

}
