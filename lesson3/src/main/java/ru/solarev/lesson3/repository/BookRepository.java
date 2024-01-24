package ru.solarev.lesson3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.solarev.lesson3.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


//  @Getter
//  private final List<Book> books;
//
//  public BookRepository() {
//    this.books = new ArrayList<>();
//  }

//  @PostConstruct
//  public void generateData() {
//    books.addAll(List.of(
//      new Book("война и мир"),
//      new Book("метрвые души"),
//      new Book("чистый код")
//    ));
//  }
//
//  public Book getBookById(long id) {
//    return books.stream().filter(it -> Objects.equals(it.getId(), id))
//      .findFirst()
//      .orElse(null);
//  }
//
//  public void deleteBook(Book book) {
//    books.remove(book);
//  }
//
//  public Book saveBook(Book book) {
//    System.out.println(book);
//    Book newBook = new Book(book.getName());
//    books.add(newBook);
//    return newBook;
//  }

}
