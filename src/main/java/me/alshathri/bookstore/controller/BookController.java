package me.alshathri.bookstore.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import me.alshathri.bookstore.model.Book;
import me.alshathri.bookstore.dto.BookDTO;
import me.alshathri.bookstore.service.BookService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        Book book = new Book(bookDTO.getTitle(), bookDTO.getAuthor(), bookDTO.getIsbn(), bookDTO.getPrice());
        Book savedBook = bookService.saveBook(book);
        BookDTO savedBookDTO = convertToDTO(savedBook);
        return new ResponseEntity<>(savedBookDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<Book> books = bookService.findAllBooks();
        List<BookDTO> bookDTOs = books.stream().map(this::convertToDTO).collect(Collectors.toList());
        return new ResponseEntity<>(bookDTOs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookService.findBookById(id);
        if (bookOptional.isPresent()) {
            BookDTO bookDTO = convertToDTO(bookOptional.get());
            return new ResponseEntity<>(bookDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Optional<Book> bookOptional = bookService.findBookById(id);
        if (bookOptional.isPresent()) {
            Book bookToUpdate = bookOptional.get();
            bookToUpdate.setTitle(bookDTO.getTitle());
            bookToUpdate.setAuthor(bookDTO.getAuthor());
            bookToUpdate.setIsbn(bookDTO.getIsbn());
            bookToUpdate.setPrice(bookDTO.getPrice());
            Book updatedBook = bookService.updateBook(bookToUpdate);
            BookDTO updatedBookDTO = convertToDTO(updatedBook);
            return new ResponseEntity<>(updatedBookDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private BookDTO convertToDTO(Book book) {
        return new BookDTO(book.getId(), book.getTitle(), book.getAuthor(), book.getIsbn(), book.getPrice());
    }
}