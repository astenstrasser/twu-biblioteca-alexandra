package com.twu.biblioteca;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ConsoleTest {

    @Test
    public void shouldWriteOutput() {
        // given
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(outContent);

        Console console = new Console(System.in, out);

        // when
        String expected = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
        console.write(expected);

        // then
        assertThat(outContent.toString()).isEqualTo(expected + "\n");
    }

    @Test
    public void shouldReturnValidUserInput() {
        // given
        byte[] input = "1".getBytes();
        Console console = new Console(new ByteArrayInputStream(input), System.out);

        // when
        int got = console.ask();

        // then
        int expected = 1;
        assertThat(got).isEqualTo(expected);
    }

    @Test
    public void shouldReturnInvalidInputConstantWhenInputIsNotInteger(){
        // given
        byte[] input = "Text".getBytes();
        Console console = new Console(new ByteArrayInputStream(input), System.out);

        // when
        int got = console.ask();

        // then
        assertThat(got).isEqualTo(Console.INVALID_INPUT);
    }

}