package ca.sheridancollege.dalmiac.controllers;

import ca.sheridancollege.dalmiac.beans.Book;
import ca.sheridancollege.dalmiac.repositories.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        model.addAttribute("newBook", new Book());
        return "index";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book newBook) {
        newBook.setCreationDate(LocalDateTime.now());
        bookRepository.save(newBook);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}/{role}")
    public String deleteBook(@PathVariable Long id, @PathVariable String role) {
        if ("ADMIN".equals(role)) {
            bookRepository.deleteById(id);
        }
        return "redirect:/";
    }

}
