package org.springexample;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Library {
    @Id
    int Libcode;
    String Libname;

    @OneToMany(targetEntity = Book.class,cascade = CascadeType.ALL)
    List<Book> bookList;
    Library(){}

    public Library(int libcode, String libname, List<Book> bookList) {
        Libcode = libcode;
        Libname = libname;
        this.bookList = bookList;
    }

    public int getLibcode() {
        return Libcode;
    }

    public void setLibcode(int libcode) {
        Libcode = libcode;
    }

    public String getLibname() {
        return Libname;
    }

    public void setLibname(String libname) {
        Libname = libname;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Library{" +
                "Libcode=" + Libcode +
                ", Libname='" + Libname + '\'' +
                ", bookList=" + bookList +
                '}';
    }
}
