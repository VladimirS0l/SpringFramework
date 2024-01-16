package ru.solarev.lesson3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solarev.lesson3.model.Book;
import ru.solarev.lesson3.repository.BookRepository;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

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
}
