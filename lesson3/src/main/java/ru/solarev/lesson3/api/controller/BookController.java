package ru.solarev.lesson3.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.solarev.lesson3.model.Book;
import ru.solarev.lesson3.service.BookService;

@RestController
@RequiredArgsConstructor
@RequestMapping("book")
public class BookController {

    public final BookService bookService;

    @GetMapping("{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Book> deleteById(@PathVariable("id") long id) {
        Book book = bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book bookCreate = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.OK).body(bookCreate);
    }
}
