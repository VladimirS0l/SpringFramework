package ru.solarev.lesson3.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.solarev.lesson3.model.Issue;
import ru.solarev.lesson3.model.Reader;
import ru.solarev.lesson3.service.ReaderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("reader")
@Tag(name = "Читатели", description = "Методы работы с читателями")
public class ReaderController {

    public final ReaderService readerService;

    @GetMapping("{id}")
    @Operation(summary = "Получить читателя по ID")
    public ResponseEntity<Reader> getReaderById(@Parameter(description = "ID читателя")
                                                    @PathVariable("id") long id) {
        Reader reader = readerService.getReaderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Удалить читателя по ID")
    public ResponseEntity<Reader> deleteById(@Parameter(description = "ID читателя")
                                                 @PathVariable("id") long id) {
        Reader reader = readerService.deleteReaderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }

    @PostMapping
    @Operation(summary = "Добавить читателя")
    public ResponseEntity<Reader> createBook(@RequestBody Reader reader) {
        Reader readerCreate = readerService.createReader(reader);
        return ResponseEntity.status(HttpStatus.OK).body(readerCreate);
    }

    @GetMapping("{id}/issue")
    @Operation(summary = "Получить все выдачи книг для читателя по ID")
    public ResponseEntity<List<Issue>> getAllIssuesByReaderId(@Parameter(description = "ID читателя")
                                                                  @PathVariable("id") long id) {
        List<Issue> issues = readerService.getAllIssuesByReaderId(id);
        return ResponseEntity.status(HttpStatus.OK).body(issues);
    }
}
