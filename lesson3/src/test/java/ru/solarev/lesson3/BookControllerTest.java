package ru.solarev.lesson3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.test.web.reactive.server.WebTestClient;
import ru.solarev.lesson3.model.Book;
import ru.solarev.lesson3.repository.BookRepository;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BookControllerTest.TestConfig.class)
@AutoConfigureWebTestClient
public class BookControllerTest {

    @TestConfiguration
    static class TestConfig {
        @Bean
        SecurityFilterChain testSecurityFilterChain(HttpSecurity security) throws Exception {
            return security.authorizeHttpRequests(reg -> reg.anyRequest().permitAll()).build();
        }
    }

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    BookRepository bookRepository;

    @Test
    public void getBookIdTest() {
        bookRepository.saveAll(List.of(
                new Book(20L, "War and Peace"),
                new Book(21L, "Hyperion")
        ));

        Book book = webTestClient.get()
                .uri("/book/21")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();
        Book bookRepos = bookRepository.findById(21L).get();

        Assertions.assertEquals(book.getId(), bookRepos.getId());
        Assertions.assertEquals(book.getName(), bookRepos.getName());
    }

    @Test
    public void deleteBookByIdTest() {
        bookRepository.saveAll(List.of(
                new Book(20L, "War and Peace"),
                new Book(21L, "Hyperion")
        ));

        webTestClient.delete()
                .uri("/book/20")
                .exchange()
                .expectStatus().isOk();

        Book bookRepos = bookRepository.findById(20L)
                .orElse(null);

        Assertions.assertEquals(null, bookRepos);
    }

    @Test
    public void createBookTest() {
        bookRepository.saveAll(List.of(
                new Book(20L, "War and Peace"),
                new Book(21L, "Hyperion")
        ));

        Book bookCreate = new Book(11L, "Interstellar");

        Book book = webTestClient.post()
                .uri("/book")
                .bodyValue(bookCreate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();

        bookRepository.save(book);

        Book bookRepos = bookRepository.findById(11L).get();

        Assertions.assertEquals(book.getId(), bookRepos.getId());
        Assertions.assertEquals(book.getName(), bookRepos.getName());
    }
}
