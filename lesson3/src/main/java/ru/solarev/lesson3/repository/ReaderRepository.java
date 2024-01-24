package ru.solarev.lesson3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.solarev.lesson3.model.Reader;

@Repository
public interface ReaderRepository extends JpaRepository<Reader, Long> {

//  @Getter
//  private final List<Reader> readers;
//
//  public ReaderRepository() {
//    this.readers = new ArrayList<>();
//  }
//
//  @PostConstruct
//  public void generateData() {
//    readers.addAll(List.of(
//      new Reader("Игорь")
//    ));
//  }
//
//  public Reader getReaderById(long id) {
//    return readers.stream().filter(it -> Objects.equals(it.getId(), id))
//      .findFirst()
//      .orElse(null);
//  }
//
//  public void deleteReader(Reader reader) {
//    readers.remove(reader);
//  }
//
//  public Reader saveReader(Reader reader) {
//    Reader newReader = new Reader(reader.getName());
//    readers.add(newReader);
//    return newReader;
//  }
}
