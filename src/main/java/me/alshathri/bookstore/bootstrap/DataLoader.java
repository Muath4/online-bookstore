package me.alshathri.bookstore.bootstrap;

import me.alshathri.bookstore.dto.UserDTO;
import me.alshathri.bookstore.model.Book;
import me.alshathri.bookstore.service.BookService;
import me.alshathri.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final BookService bookService;
    private final UserService userService;

    @Autowired
    public DataLoader(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {
        System.out.println("DataLoader is running...");
        loadSampleBooks();
        loadSampleUsers();
    }

    private void loadSampleBooks() {
        Book book1 = new Book("The Catcher in the Rye", "J.D. Salinger", "978-0316769488", 75.0);
        bookService.saveBook(book1);

        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "978-0060935467", 49.9);
        bookService.saveBook(book2);

        Book book3 = new Book("1984", "George Orwell", "978-0451524935", 25.0);
        bookService.saveBook(book3);

        System.out.println("Loaded sample books");
    }

    private void loadSampleUsers() {
        UserDTO user1 = new UserDTO("John", "password123", "ADMIN");
        userService.saveUser(user1);

        UserDTO user2 = new UserDTO("Jane", "password456", "USER");
        userService.saveUser(user2);

        System.out.println("Loaded sample users");
    }
}