package com.twu.biblioteca;

public class Book {

  private String title;
  private String author;
  private int publicationYear;

  public Book(String title, String author, int publicationYear) {
    this.title = title;
    this.author = author;
    this.publicationYear = publicationYear;
  }

  public String getAuthor() {
    return author;
  }

  public int getPublicationYear() {
    return publicationYear;
  }

  public String getTitle() {
    return title;
  }

  @Override
  public String toString() {
    return String.format("%s - %s (%s)", this.author, this.title, this.publicationYear);
  }
}
