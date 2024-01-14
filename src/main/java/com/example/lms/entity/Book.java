package com.example.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {
    @Id
    private long id;
    private String ISBN;
    private String title;
    private String author;
    private String genre;
    private long availableCopies;
}
