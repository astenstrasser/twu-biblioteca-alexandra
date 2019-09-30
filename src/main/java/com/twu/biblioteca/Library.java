package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Library {

  private List<Book> books = new ArrayList<>();

  public void addBook(Book book) {
    this.books.add(book);
  }

  public List<Book> getBooks() {
    return this.books;
  }
}
