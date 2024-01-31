package ru.solarev.lesson3.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.solarev.lesson3.model.Book;
import ru.solarev.lesson3.service.BookService;

@RestController
@RequiredArgsConstructor
@RequestMapping("book")
@Tag(name = "Книги", description = "Методы работы с книгами")
public class BookController {

    public final BookService bookService;

    @GetMapping("{id}")
    @Operation(summary = "Запрос на получение книги по ID")
    public ResponseEntity<Book> getBookById(@Parameter(description = "ID книги")
                                                @PathVariable("id") long id) {
        Book book = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удаление книги по ID")
    public ResponseEntity<Book> deleteById(@Parameter(description = "ID книги")
                                               @PathVariable("id") long id) {
        Book book = bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @PostMapping
    @Operation(summary = "Создать книгу")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book bookCreate = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.OK).body(bookCreate);
    }
}
