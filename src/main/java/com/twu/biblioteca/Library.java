package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Library {

  //    TODO: testar funções da library separadamente

  private List<Book> books = new ArrayList<>();

  public void addBook(Book book) {
    this.books.add(book);
  }

  public List<Book> getBooks() {
    return this.books;
  }

  public List<Book> getAvailableBooks() {

//    TODO: implementar usando stream

    List<Book> availableBooks = new ArrayList<>();

    for (Book book : this.books) {
      if (book.isAvailable()) {
        availableBooks.add(book);
      }
    }

    return availableBooks;
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

    if (isBookOnLibrary(bookId) && !book.isAvailable()) {
      book.returnBook();
    } else {
      throw new NoSuchElementException();
    }
  }

  public Book searchBookById(String bookId) {
    Book book = null;
    for (Book b : this.books) {
      if ((b.getId().toString()).equals(bookId)) {
        book = b;
      }
    }
    return book;
  }

  public boolean isBookOnLibrary(String bookId) {
    boolean isBookOnLibrary = false;
    for (Book b : this.books) {
      if ((b.getId().toString()).equals(bookId)) {
        isBookOnLibrary = true;
      }
    }
    return isBookOnLibrary;
  }
}
