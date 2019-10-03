package com.twu.biblioteca;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {

  private Scanner scanner;
  private PrintStream out;
  public static final int INVALID_INPUT = -1;

  public Console(InputStream inputStream, PrintStream out) {
    this.scanner = new Scanner(inputStream);
    this.out = out;
  }

  public void write(String text) {
    this.out.println(text);
  }

  public int askInt() {
    int number;
    try {
      number = scanner.nextInt();
    } catch (InputMismatchException e) {
      number = INVALID_INPUT;
      scanner.nextLine();
    }
    return number;
  }

  public String askText() {
    String text;
    try {
      text = scanner.next();
    } catch (InputMismatchException e) {
      text = null;
    }
    return text;
  }
}
