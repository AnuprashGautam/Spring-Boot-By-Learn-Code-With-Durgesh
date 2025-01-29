@DeleteMapping("/books/{id}")
public void deleteBook(@PathVariable("id")int id)
{
    this.bookService.deleteBook(id);
}
