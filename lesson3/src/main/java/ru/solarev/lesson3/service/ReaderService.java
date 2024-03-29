package ru.solarev.lesson3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.solarev.lesson3.model.Book;
import ru.solarev.lesson3.model.Issue;
import ru.solarev.lesson3.model.Reader;
import ru.solarev.lesson3.repository.IssueRepository;
import ru.solarev.lesson3.repository.ReaderRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Reader getReaderById(Long id) {
        return readerRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Не найден читатель с идентификатором \"" + id + "\""));
    }

    public Reader deleteReaderById(long id) {
        Reader reader = getReaderById(id);
        readerRepository.delete(reader);
        return reader;
    }

    public Reader createReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public List<Issue> getAllIssuesByReaderId(long idReader) {
        return issueRepository.findAllByReaderId(idReader);
    }

    public List<Reader> getAllReadersWhoAllowedBook() {
        Set<Long> usersAllowedBook = issueRepository.findAll()
                .stream()
                .filter(issue -> issue.getReturned_at() == null)
                .map(Issue::getReaderId)
                .collect(Collectors.toSet());
        return readerRepository.findAll()
                .stream()
                .filter(reader -> usersAllowedBook.contains(reader.getId()))
                .collect(Collectors.toList());
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }
}
