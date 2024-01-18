package ru.solarev.lesson3.repository;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Repository;
import ru.solarev.lesson3.model.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ReaderRepository {

  @Getter
  private final List<Reader> readers;

  public ReaderRepository() {
    this.readers = new ArrayList<>();
  }

  @PostConstruct
  public void generateData() {
    readers.addAll(List.of(
      new Reader("Игорь")
    ));
  }

  public Reader getReaderById(long id) {
    return readers.stream().filter(it -> Objects.equals(it.getId(), id))
      .findFirst()
      .orElse(null);
  }

  public void deleteReader(Reader reader) {
    readers.remove(reader);
  }

  public Reader saveReader(Reader reader) {
    Reader newReader = new Reader(reader.getName());
    readers.add(newReader);
    return newReader;
  }
}
