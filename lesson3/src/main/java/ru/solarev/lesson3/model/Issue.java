package ru.solarev.lesson3.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
@Getter
@Setter
@NoArgsConstructor
public class Issue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private long bookId;
  private long readerId;

  private LocalDateTime issued_at;
  private LocalDateTime returned_at;

  public Issue(long bookId, long readerId, LocalDateTime issued_at) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.issued_at = issued_at;
  }
}
