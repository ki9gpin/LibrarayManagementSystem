package com.example.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long booksCheckedOut;
}
