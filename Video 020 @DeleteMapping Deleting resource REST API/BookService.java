// Deleting the book with the help of id.
public void deleteBook(int id) {
    list = list.stream().filter(book -> book.getId() != id).collect(Collectors.toList());
}