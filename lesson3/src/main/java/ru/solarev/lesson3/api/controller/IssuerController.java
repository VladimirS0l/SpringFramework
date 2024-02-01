package ru.solarev.lesson3.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.solarev.lesson3.api.dto.IssueRequest;
import ru.solarev.lesson3.model.Issue;
import ru.solarev.lesson3.service.IssuerService;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("issue")
@Tag(name = "Выдачи книг", description = "Методы для выдачи книг")
public class IssuerController {

  private final IssuerService service;

  @PutMapping("{issueId}")
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Вернуть книгу по ID")
  public void returnBook(@Parameter(name = "ID выдачи") @PathVariable("issueId") long issueId) {
    service.returnBook(issueId);
  }

  @PostMapping
  @Operation(summary = "Выдать книгу")
  public ResponseEntity<Issue> issueBook(@RequestBody IssueRequest request) {
    log.info("Получен запрос на выдачу: readerId = {}, bookId = {}", request.getReaderId(), request.getBookId());

    final Issue issue;
    try {
      issue = service.issue(request);
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.status(HttpStatus.CONFLICT).body(issue);
  }

  @GetMapping("{id}")
  @Operation(summary = "Получить информацию о выдаче книги по ID")
  public ResponseEntity<Issue> getIssueById(@Parameter(name = "ID выдачи")
                                              @PathVariable("id") long id) {
    Issue issue = service.getIssueById(id);
    return ResponseEntity.status(HttpStatus.OK).body(issue);
  }

}
