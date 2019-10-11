package com.twu.biblioteca;

import java.util.UUID;

public interface LibraryItem {

  public String getTitle();

  public int getPublicationYear();

  public String getAuthor();

//  public String toString();

  public UUID getId();

  public boolean isAvailable();

  public void checkout();

  public void returnToLibrary();
}
