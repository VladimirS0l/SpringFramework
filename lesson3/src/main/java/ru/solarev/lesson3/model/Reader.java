package ru.solarev.lesson3.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "readers")
@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Модель читателя")
@AllArgsConstructor
public class Reader {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "ID читателя")
  private long id;
  @Schema(description = "Имя читателя")
  private String name;

}
