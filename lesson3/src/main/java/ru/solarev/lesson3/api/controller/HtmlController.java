package ru.solarev.lesson3.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.solarev.lesson3.model.Book;
import ru.solarev.lesson3.model.Issue;
import ru.solarev.lesson3.model.Reader;
import ru.solarev.lesson3.service.BookService;
import ru.solarev.lesson3.service.IssuerService;
import ru.solarev.lesson3.service.ReaderService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("ui")
public class HtmlController {
    private final BookService bookService;
    private final ReaderService readerService;
    private final IssuerService issuerService;

    @GetMapping("books")
    public String getAllFreeBooks(Model model) {
        List<Book> books = bookService.getAllFreeBook();
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("reader")
    public String getAllReadersWhoAllowedBook(Model model) {
        List<Reader> readers = readerService.getAllReadersWhoAllowedBook();
        model.addAttribute("readers", readers);
        return "readers";
    }

    @GetMapping("issues")
    public String getAllIssues(Model model) {
        List<Issue> issues = issuerService.getAllIssues();
        model.addAttribute("issues", issues);
        return "issues";
    }

    @GetMapping("reader/{id}")
    public String getAllIssues(@PathVariable("id") long id, Model model) {
        Reader reader = readerService.getReaderById(id);
        List<Book> books = bookService.getBooksByReaderId(id);

        model.addAttribute("reader", reader);
        model.addAttribute("books", books);
        return "reader";
    }

}
