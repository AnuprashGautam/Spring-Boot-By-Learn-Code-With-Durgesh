package com.api.book.bootrestbook.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.api.book.bootrestbook.entities.Book;

@Component
public class BookService {
    private static List<Book> list=new ArrayList<>();

    static{
        list.add(new Book(1,"Java Complete Reference","XYZ"));
        list.add(new Book(2,"Head First Java","ABC"));
        list.add(new Book(3,"Java","abc"));
    }

    // Get all the books.
    public List<Book> getAllBooks(){
        return list;
    }

    // Get the specific book.
    public Book getBookById(int id){
        Book book=null;
        book=list.stream().filter(e->e.getId()==id).findFirst().get();
        return book;
    }
}
