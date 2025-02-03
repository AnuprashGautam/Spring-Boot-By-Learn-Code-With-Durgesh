package com.api.book.bootrestbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.bootrestbook.entities.Book;
import com.api.book.bootrestbook.services.BookService;


//RestController=Controller+ResponseBody
@RestController
public class BookController {
    // // @RequestMapping(value = "/books" ,method=RequestMethod.GET)
    // @GetMapping(value = "/books")
    // // @ResponseBody
    // public Book getBooks(){
    //     Book book=new Book();
    //     book.setId(1);
    //     book.setTitle("Java Complete Reference");
    //     book.setAuthor("XYZ");
    //     return book;
    // }

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getBooks(){
        return this.bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable("id")int id){
        return this.bookService.getBookById(id);
    }
}
