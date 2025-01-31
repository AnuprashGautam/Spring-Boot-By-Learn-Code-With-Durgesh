package com.api.book.bootrestbook.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    // Book book=new Book();
    // book.setId(1);
    // book.setTitle("Java Complete Reference");
    // book.setAuthor("XYZ");
    // return book;
    // }
    @Autowired
    private BookService bookService;

    // Getting all the books.
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> list = this.bookService.getAllBooks();
        if (list.size() <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }
    // Getting a specific book.
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {
        Book book = this.bookService.getBookById(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }

    // Creating a new book.
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book b = null;
        try {
            b = this.bookService.addBook(book);
            return ResponseEntity.status(HttpStatus.CREATED).body(b);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Deleting a book.
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        try {
            this.bookService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Updating the book.
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("id") int bookId) {
        try {
            this.bookService.updateBook(book, bookId);
            return ResponseEntity.ok().body(book);
            // return ResponseEntity.status(HttpStatus.CREATED).body("Hello");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
