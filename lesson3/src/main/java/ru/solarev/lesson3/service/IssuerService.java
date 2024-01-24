package ru.solarev.lesson3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.solarev.lesson3.api.dto.IssueRequest;
import ru.solarev.lesson3.model.Book;
import ru.solarev.lesson3.model.Issue;
import ru.solarev.lesson3.model.Reader;
import ru.solarev.lesson3.repository.BookRepository;
import ru.solarev.lesson3.repository.IssueRepository;
import ru.solarev.lesson3.repository.ReaderRepository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class IssuerService {

  private final BookRepository bookRepository;
  private final ReaderRepository readerRepository;
  private final IssueRepository issueRepository;

  @Value("${application.issue.max-allowed-books:#{1}}")
  private int maxAllowedBooks;

  public Issue issue(IssueRequest request) {

    Book book = bookRepository.findById(request.getBookId())
            .orElseThrow(() ->
                    new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\""));

    Reader reader = readerRepository.findById(request.getReaderId())
            .orElseThrow(() ->
                    new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\""));
    if (issueRepository.findAllByReaderId(request.getReaderId()).size() >= maxAllowedBooks) {
      throw new NoSuchElementException("Читатель не может взять больше \"" + maxAllowedBooks + "\" книг");
    }

    Issue issue = new Issue(request.getBookId(), request.getReaderId(), LocalDateTime.now());
    return issueRepository.save(issue);
  }

  public Issue getIssueById(long id) {
    return issueRepository.findById(id)
            .orElseThrow(() ->
                    new NoSuchElementException("Не найдена запись с идентификатором \"" + id + "\""));
  }

  public void returnBook(long issueId) {
    Issue issue = getIssueById(issueId);
    issue.setReturned_at(LocalDateTime.now());
    issueRepository.save(issue);
  }

  public List<Issue> getAllIssues() {
    return issueRepository.findAll();
  }
}
