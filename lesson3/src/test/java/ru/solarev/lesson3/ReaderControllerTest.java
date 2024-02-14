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
import ru.solarev.lesson3.model.Reader;
import ru.solarev.lesson3.repository.BookRepository;
import ru.solarev.lesson3.repository.ReaderRepository;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BookControllerTest.TestConfig.class)
@AutoConfigureWebTestClient
public class ReaderControllerTest {
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
    ReaderRepository readerRepository;

    @Test
    public void getReaderIdTest() {
        readerRepository.saveAll(List.of(
                new Reader(100L, "Vladimir"),
                new Reader(101L, "Alice")
        ));

        Reader reader = webTestClient.get()
                .uri("/reader/100")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Reader.class)
                .returnResult()
                .getResponseBody();

        Reader readerData = readerRepository.findById(100L).get();

        Assertions.assertEquals(reader.getId(), readerData.getId());
        Assertions.assertEquals(reader.getName(), readerData.getName());
    }

    @Test
    public void deleteReaderByIdTest() {
        readerRepository.saveAll(List.of(
                new Reader(100L, "Vladimir"),
                new Reader(101L, "Alice")
        ));

        webTestClient.delete()
                .uri("/reader/101")
                .exchange()
                .expectStatus().isOk();

        Reader reader = readerRepository.findById(101L)
                .orElse(null);

        Assertions.assertEquals(null, reader);
    }

    @Test
    public void createBookTest() {
        readerRepository.saveAll(List.of(
                new Reader(100L, "Vladimir"),
                new Reader(101L, "Alice")
        ));

        Reader createReader = new Reader(102L, "Ivan");

        Reader reader = webTestClient.post()
                .uri("/reader")
                .bodyValue(createReader)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Reader.class)
                .returnResult()
                .getResponseBody();

        readerRepository.save(reader);

        Reader readerRepos = readerRepository.findById(102L)
                .orElse(null);

        Assertions.assertEquals(reader.getId(), readerRepos.getId());
        Assertions.assertEquals(reader.getName(), readerRepos.getName());
    }


}

