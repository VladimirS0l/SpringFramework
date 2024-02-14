package ru.solarev.lesson3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "issues")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель выдачи")
public class Issue {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Schema(description = "ID книги")
  private long bookId;
  @Schema(description = "ID читателя")
  private long readerId;
  @Schema(description = "Дата/Время выдачи")
  private LocalDateTime issued_at;
  @Schema(description = "Дата/Время возврата")
  private LocalDateTime returned_at;

  public Issue(long bookId, long readerId, LocalDateTime issued_at) {
    this.bookId = bookId;
    this.readerId = readerId;
    this.issued_at = issued_at;
  }
}
