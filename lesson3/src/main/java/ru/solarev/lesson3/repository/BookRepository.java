package ru.solarev.lesson3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.solarev.lesson3.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
