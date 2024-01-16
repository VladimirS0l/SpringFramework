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

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public Reader getReaderById(Long id) {
        Reader reader = readerRepository.getReaderById(id);
        if (reader == null) {
            throw new NoSuchElementException("Не найден читатель с идентификатором \"" + id + "\"");
        }
        return reader;
    }

    public Reader deleteReaderById(long id) {
        Reader reader = getReaderById(id);
        readerRepository.deleteReader(reader);
        return reader;
    }

    public Reader createReader(Reader reader) {
        return readerRepository.saveReader(reader);
    }

    public List<Issue> getAllIssuesByReaderId(long idReader) {
        return issueRepository.findAllIssuesByReaderId(idReader);
    }
}
