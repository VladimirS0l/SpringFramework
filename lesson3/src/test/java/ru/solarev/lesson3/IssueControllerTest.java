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
import ru.solarev.lesson3.model.Issue;
import ru.solarev.lesson3.repository.IssueRepository;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = BookControllerTest.TestConfig.class)
@AutoConfigureWebTestClient
public class IssueControllerTest {

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
    IssueRepository issueRepository;

    @Test
    public void returnBookTest() {
        issueRepository.saveAll(List.of(
                new Issue(1L, 20L, 100L,  LocalDateTime.now(), null),
                new Issue(2L, 21L, 101L, LocalDateTime.now(),null)
        ));


        webTestClient.put()
                .uri("/issue/1")
                .exchange()
                .expectStatus().isOk();

        Issue issue = issueRepository.findById(1L).orElse(null);

        Assertions.assertNotEquals(null, issue.getReturned_at());
    }

    @Test
    public void issueBookTest() {
        issueRepository.saveAll(List.of(
                new Issue(1L, 20L, 100L,  LocalDateTime.now(), null),
                new Issue(2L, 21L, 101L, LocalDateTime.now(),null)
        ));

        Issue issue = new Issue(3L, 22L, 104L, LocalDateTime.now(), null);


        Issue getIssue = webTestClient.post()
                .uri("/issue")
                .bodyValue(issue)
                .exchange()
                .expectBody(Issue.class)
                .returnResult()
                .getResponseBody();

        Issue createIssue = issueRepository.findById(3L).orElse(null);

        Assertions.assertEquals(getIssue, createIssue);
    }

    @Test
    public void getBookTest() {
        issueRepository.saveAll(List.of(
                new Issue(1L, 20L, 100L,  LocalDateTime.now(), null),
                new Issue(2L, 21L, 101L, LocalDateTime.now(),null)
        ));


        Issue getIssue = webTestClient.get()
                .uri("/issue/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Issue.class)
                .returnResult()
                .getResponseBody();

        Issue issue = issueRepository.findById(1L).orElse(null);

        Assertions.assertEquals(getIssue.getId(), issue.getId());
        Assertions.assertEquals(getIssue.getBookId(), issue.getBookId());
        Assertions.assertEquals(getIssue.getIssued_at(), issue.getIssued_at());
    }

}
