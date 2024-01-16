package ru.solarev.lesson3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.solarev.lesson3.api.dto.IssueRequest;
import ru.solarev.lesson3.model.Issue;
import ru.solarev.lesson3.repository.BookRepository;
import ru.solarev.lesson3.repository.IssueRepository;
import ru.solarev.lesson3.repository.ReaderRepository;


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

    if (bookRepository.getBookById(request.getBookId()) == null) {
      throw new NoSuchElementException("Не найдена книга с идентификатором \"" + request.getBookId() + "\"");
    }
    if (readerRepository.getReaderById(request.getReaderId()) == null) {
      throw new NoSuchElementException("Не найден читатель с идентификатором \"" + request.getReaderId() + "\"");
    }
    if (issueRepository.findReaderAllowedBooks(request.getReaderId()).size() >= maxAllowedBooks) {
      throw new NoSuchElementException("Читатель не может взять больше \"" + maxAllowedBooks + "\" книг");
    }


    Issue issue = new Issue(request.getBookId(), request.getReaderId());
    issueRepository.save(issue);
    return issue;
  }

  public Issue getIssueById(long id) {
    Issue issue = issueRepository.findIssueById(id);
    if (issue == null) {
      throw new NoSuchElementException("Не найдена запись с идентификатором \"" + id + "\"");
    }
    return issue;
  }

  public int getAllow() {
    return maxAllowedBooks;
  }

  public void returnBook(long issueId) {
    issueRepository.returnBook(issueId);
  }

}
