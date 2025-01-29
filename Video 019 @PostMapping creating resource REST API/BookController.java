@PostMapping("/books")
public Book addBook(@RequestBody Book book)
{
    Book b=this.bookService.addBook(book);
    return b;
}
