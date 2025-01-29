// Updating the book.
public void updatBook(Book updatedBook, int bookId) {
    list = list.stream().map(b -> {
        if (b.getId() == bookId) {
            b.setTitle(updatedBook.getTitle());
            b.setAuthor(updatedBook.getAuthor());
        }
        return b;
    }).collect(Collectors.toList());
}