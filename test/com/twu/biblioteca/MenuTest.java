package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MenuTest {

    Library library;
    Book bookOne;
    Book bookTwo;

    @Before
    public void TestSetup(){
        this.library = new Library();
        this.bookOne = new Book("Fahrenheit 451", "Ray Bradbury", 1953);
        this.bookTwo = new Book("Slaughterhouse-Five", "Kurt Vonnegut", 1969);

        library.addBook(bookOne);
        library.addBook(bookTwo);
    }

    @Test
    public void shouldShowListOfBooksWhenUserChooseOptionOne() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        int inputOption = 1;
        library.menu.redirectToUsersOption(inputOption);
        assertThat(outContent.toString(), CoreMatchers.containsString("Kurt Vonnegut"));
        assertThat(outContent.toString(), CoreMatchers.containsString("Ray Bradbury"));
    }


    @Test
    public void shouldNotifyWhenUserChooseInvalidOption() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        int inputOption = -3;
        boolean isOptionValid = library.menu.redirectToUsersOption(inputOption);
        assertThat(outContent.toString(), CoreMatchers.containsString("Please select a valid option"));
        assertThat(isOptionValid, is(equalTo(false)));
    }

//    @Test
//    public void shouldShowMenuAgainWhenInvalidOption() {
//        Scanner scannerMock = mock(Scanner.class, "scannerMock");
//        verify(scannerMock, times(2)).nextInt(2);
//
//    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExitWhenSelectedExitOptionOnMenu(){
        exit.expectSystemExitWithStatus(0);
        library.menu.redirectToUsersOption(0);
    }

//    @Test
//    public void shouldReturnToMenuWhenInputGivenIsNotInteger(){
//        final ByteArrayOutputStream outContent = new ByteArrayOutputStreamArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//        String inputOption = "a";
//        boolean isOptionValid = library.menu.redirectToUsersOption(inputOption);
//        assertThat(outContent.toString(), CoreMatchers.containsString("Please select a valid option"));
//        assertThat(isOptionValid, is(equalTo(false)));
//    }

}
