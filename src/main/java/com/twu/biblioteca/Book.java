package com.twu.biblioteca;

import java.util.UUID;

public class Book {

  private String title;
  private String author;
  private int publicationYear;
  private UUID id;
  private boolean isAvailable;

  public Book(String title, String author, int publicationYear) {
    this.title = title;
    this.author = author;
    this.publicationYear = publicationYear;
    this.id = UUID.randomUUID();
    this.isAvailable = true;
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
    return String.format("[ID: %s]      %s - %s (%s)", this.id, this.author, this.title, this.publicationYear);
  }

  public UUID getId() {
    return this.id;
  }

  public boolean isAvailable(){
    return this.isAvailable;
  }


  public void checkout() {
    this.isAvailable = false;
  }
}
