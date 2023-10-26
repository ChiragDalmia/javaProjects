package ca.sheridancollege.dalmiac.services;

import ca.sheridancollege.dalmiac.beans.Book;
import ca.sheridancollege.dalmiac.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void saveBook(Book book) {
        book.setCreationDate(LocalDateTime.now());
        bookRepository.save(book);
    }

    public void deleteBook(Long id, String role) {
        if ("ADMIN".equals(role)) {
            bookRepository.deleteById(id);
        }
    }
}
