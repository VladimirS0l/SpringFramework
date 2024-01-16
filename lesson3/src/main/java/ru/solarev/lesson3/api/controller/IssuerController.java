package ru.solarev.lesson3.api.controller;

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
@RequestMapping("/issue")
public class IssuerController {

  private final IssuerService service;

  @PutMapping("{issueId}")
  @ResponseStatus(HttpStatus.OK)
  public void returnBook(@PathVariable("issueId") long issueId) {
    service.returnBook(issueId);
  }

  @PostMapping
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
  public ResponseEntity<Issue> getIssueById(@PathVariable("id") long id) {
    Issue issue = service.getIssueById(id);
    return ResponseEntity.status(HttpStatus.OK).body(issue);
  }

}
