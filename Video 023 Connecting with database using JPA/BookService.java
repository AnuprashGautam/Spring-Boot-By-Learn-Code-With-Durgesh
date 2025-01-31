package com.api.book.bootrestbook.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Component;

import com.api.book.bootrestbook.dao.BookRepository;
import com.api.book.bootrestbook.entities.Book;

@Component
public class BookService {
    // private static List<Book> list=new ArrayList<>();

    // static{
    //     list.add(new Book(1,"Java Complete Reference","XYZ"));
    //     list.add(new Book(2,"Head First Java","ABC"));
    //     list.add(new Book(3,"Java","abc"));
    // }

    @Autowired
    private BookRepository bookRepository;

    // Get all the books.
    public List<Book> getAllBooks(){
        List<Book> list=(List<Book>)this.bookRepository.findAll();
        return list;
    }

    // Get the specific book.
    public Book getBookById(int id){
        Book book=null;
        try {
            // book=list.stream().filter(e->e.getId()==id).findFirst().get();

            book=this.bookRepository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }
    // Adding the book to the list.
    public Book addBook(Book book) {
        try {
            // Save the book entity
            return bookRepository.save(book);
        } catch (ObjectOptimisticLockingFailureException e) {
            // Handle the optimistic locking failure
            System.err.println("Optimistic locking failure: " + e.getMessage());
            throw e; // or handle it in another way
        }
    }

    // Deleting the book with the help of id.
    public void deleteBook(int id) {
        // list=list.stream().filter(book->book.getId()!=id).collect(Collectors.toList());
        bookRepository.deleteById(id);
    }

    // Updating the book.
    public void updateBook(Book updatedBook, int bookId) {
        // list=list.stream().map(b->{
        //     if(b.getId()==bookId)
        //     {
        //         b.setTitle(updatedBook.getTitle());
        //         b.setAuthor(updatedBook.getAuthor());
        //     }
        //     return b;
        // }).collect(Collectors.toList());

        updatedBook.setId(bookId);
        bookRepository.save(updatedBook);
    }
}
