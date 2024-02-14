package ru.solarev.lesson3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.mapping.TableOwner;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель книги")
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "ID книги")
  private long id;
  @Schema(description = "Название книги")
  private String name;


}
