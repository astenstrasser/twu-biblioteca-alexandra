package com.twu.biblioteca;

import org.hamcrest.CoreMatchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class MenuTest {

    @Test
    public void shouldShowListOfBooksWhenUserChooseOptionOne() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        int inputOption = 1;
        Menu.redirectToUsersOption(inputOption);

        assertThat(outContent.toString(), CoreMatchers.containsString("Kurt Vonnegut"));
        assertThat(outContent.toString(), CoreMatchers.containsString("Ray Bradbury"));
    }


    @Test
    public void shouldNotifyWhenUserChooseInvalidOption() {
        final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        int inputOption = -3;
        boolean isOptionValid = Menu.redirectToUsersOption(inputOption);
        assertThat(outContent.toString(), CoreMatchers.containsString("Please select a valid option"));
        assertThat(isOptionValid, is(equalTo(false)));
    }

    @Test
    public void shouldShowBackMenuWhenInvalidOption() {
//      Simular input do usuário?
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void shouldExitWhenSelectedExitOptionOnMenu(){
        exit.expectSystemExitWithStatus(0);
        Menu.redirectToUsersOption(0);
    }

}
