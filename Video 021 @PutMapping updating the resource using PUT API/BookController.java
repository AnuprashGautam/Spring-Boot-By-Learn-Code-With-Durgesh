@PutMapping("/books/{id}")
public void updatBook(@RequestBody Book book, @PathVariable("id") int bookId) {
    this.bookService.updatBook(book, bookId);
}