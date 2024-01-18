package ru.solarev.lesson3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solarev.lesson3.model.Book;
import ru.solarev.lesson3.model.Issue;
import ru.solarev.lesson3.repository.BookRepository;
import ru.solarev.lesson3.repository.IssueRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;

    public Book getBookById(Long id) {
        Book book = bookRepository.getBookById(id);
        if (book == null) {
            throw new NoSuchElementException("Не найдена книга с идентификатором \"" + id + "\"");
        }
        return book;
    }

    public Book deleteBookById(long id) {
        Book book = getBookById(id);
        bookRepository.deleteBook(book);
        return book;
    }

    public Book createBook(Book book) {
        return bookRepository.saveBook(book);
    }

    public List<Book> getAllFreeBook() {
        Set<Long> allowedBookId = issueRepository.getIssues().stream()
                .filter(book -> book.getReturned_at() == null)
                .map(Issue::getBookId)
                .collect(Collectors.toSet());
        List<Book> books = bookRepository.getBooks();
        return books.stream().filter(book -> !allowedBookId.contains(book.getId()))
                .collect(Collectors.toList());
    }

    public List<Book> getBooksByReaderId(long readerId) {
        Set<Long> booksId = issueRepository.getIssues()
                .stream()
                .filter(issue -> issue.getReaderId() == readerId)
                .map(Issue::getBookId)
                .collect(Collectors.toSet());
        List<Book> books = bookRepository.getBooks();
        return books.stream().filter(book -> booksId.contains(book.getId()))
                .collect(Collectors.toList());

    }

    public List<Book> getAllBooks() {
        return bookRepository.getBooks();
    }
}
