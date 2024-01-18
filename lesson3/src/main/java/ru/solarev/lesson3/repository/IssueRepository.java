package ru.solarev.lesson3.repository;

import lombok.Getter;
import org.springframework.stereotype.Repository;
import ru.solarev.lesson3.model.Issue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IssueRepository {

  @Getter
  private final List<Issue> issues;

  public IssueRepository() {
    this.issues = new ArrayList<>();
  }

  public Issue findIssueById(long id) {
    return issues.stream().filter(issue -> issue.getId() == id)
            .findFirst()
            .orElse(null);
  }

  public List<Issue> findReaderAllowedBooks(long readerId) {
    return issues.stream().filter(issue -> issue.getReaderId() == readerId)
            .collect(Collectors.toList());
  }

  public void save(Issue issue) {
    // insert into ....
    issues.add(issue);
  }

  public List<Issue> findAllIssuesByReaderId(long idReader) {
    return issues.stream().filter(issue -> issue.getReaderId() == idReader)
            .collect(Collectors.toList());
  }

  public void returnBook(long issueId) {
    issues.stream().filter(issue -> issue.getId() == issueId)
            .forEach(c -> c.setReturned_at(LocalDateTime.now()));
  }

}