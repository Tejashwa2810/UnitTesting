package com.example.Project1.Controller;

import com.example.Project1.Exceptions.BookNotFoundException;
import com.example.Project1.Models.Books;
import com.example.Project1.Service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public Books createBook(Books book){
        return bookService.createBook(book);
    }

    public List<Books> createMultipleBooks(List<Books> bookList){
        return bookService.createMultipleBooks(bookList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable int id){
        Optional<Books> book = bookService.getBookById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        } else {
            throw new BookNotFoundException("Book not found with id: " + id);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Page<Books>> getAllBooks(@RequestParam(defaultValue = "0") int page){
        Page<Books> books = bookService.getAllBooks(page);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Books>> getBooksByTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page) {
        Page<Books> books = bookService.getBooksByName(title, page);
        return ResponseEntity.ok(books);
    }

    @PutMapping("/update")
    public ResponseEntity<Books> updateBook(@RequestBody Books book){
        Optional<Books> updatedBook = bookService.updateBook(book);
        return ResponseEntity.ok(updatedBook.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable int id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Book deleted successfully with id: " + id);
    }

    @GetMapping("/addnum")
    public int addTwoNumbers(int a, int b){
        return bookService.addTwoNumbers(a,b);
    }
}
