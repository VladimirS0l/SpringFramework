package ru.solarev.lesson3.api.controller;

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
public class ReaderController {

    public final ReaderService readerService;

    @GetMapping("{id}")
    public ResponseEntity<Reader> getReaderById(@PathVariable("id") long id) {
        Reader reader = readerService.getReaderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Reader> deleteById(@PathVariable("id") long id) {
        Reader reader = readerService.deleteReaderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(reader);
    }

    @PostMapping
    public ResponseEntity<Reader> createBook(@RequestBody Reader reader) {
        Reader readerCreate = readerService.createReader(reader);
        return ResponseEntity.status(HttpStatus.OK).body(readerCreate);
    }

    @GetMapping("{id}/issue")
    public ResponseEntity<List<Issue>> getAllIssuesByReaderId(@PathVariable("id") long id) {
        List<Issue> issues = readerService.getAllIssuesByReaderId(id);
        return ResponseEntity.status(HttpStatus.OK).body(issues);
    }


}
