@PutMapping("/books/{id}")
public Book updatBook(@RequestBody Book book, @PathVariable("id") int bookId) {
    return this.bookService.updatBook(book, bookId);
}