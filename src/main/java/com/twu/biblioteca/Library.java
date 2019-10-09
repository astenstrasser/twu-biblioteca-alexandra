package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class Library {

  private List<Book> books = new ArrayList<>();

  public void addBook(Book book) {
    this.books.add(book);
  }

  public List<Book> getBooks() {
    return this.books;
  }

  public List<Book> getAvailableBooks() {
    return this.books.stream().filter(Book::isAvailable).collect(Collectors.toList());
  }

  public void checkoutBook(String givenBookId) {
    Book book = null;
    if (isBookOnLibrary(givenBookId)) {
      book = searchBookById(givenBookId);
    }

    if (isBookOnLibrary(givenBookId) && book.isAvailable()) {
      book.checkout();
    } else {
      throw new NoSuchElementException();
    }
  }

  public void returnBook(String bookId) {
    Book book = null;
    if (isBookOnLibrary(bookId)) {
      book = searchBookById(bookId);
    }

    if (book != null && !book.isAvailable()) {
      book.returnBook();
    } else {
      throw new NoSuchElementException();
    }
  }

  public Book searchBookById(String bookId) {
    return this.books.stream()
        .filter(b -> b.getId().toString().equals(bookId))
        .findAny()
        .orElse(null);
  }

  public boolean isBookOnLibrary(String bookId) {
    boolean isBookOnLibrary =
        !(this.books.stream().filter(b -> b.getId().toString().equals(bookId)).count() == 0);

    if (isBookOnLibrary) {
      return true;
    } else {
      return false;
    }
  }
}
