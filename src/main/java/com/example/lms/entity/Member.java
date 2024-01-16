package com.example.lms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_id_generator")
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private long booksCheckedOut;

    public Member() {
    }

    public Member(String firstName, String lastName, String email, long booksCheckedOut) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.booksCheckedOut = booksCheckedOut;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBooksCheckedOut() {
        return booksCheckedOut;
    }

    public void setBooksCheckedOut(long booksCheckedOut) {
        this.booksCheckedOut = booksCheckedOut;
    }
}
