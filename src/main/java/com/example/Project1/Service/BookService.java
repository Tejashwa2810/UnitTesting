package com.example.Project1.Service;

import com.example.Project1.Exceptions.BookNotFoundException;
import com.example.Project1.Models.Books;
import com.example.Project1.Repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Books createBook(Books book){
        return bookRepository.save(book);
    }

    public List<Books> createMultipleBooks(List<Books> booksList){
        List<Books> savedBooks = new ArrayList<>();
        for(Books b : booksList){
            bookRepository.save(b);
            savedBooks.add(b);
        }
        return savedBooks;
    }

    public Optional<Books> getBookById(int id){
        return bookRepository.findById(id);
    }

    public Page<Books> getAllBooks(int pageno){
        Pageable pageable = PageRequest.of(pageno, 10);
        return bookRepository.findAll(pageable);
    }

    public Page<Books> getBooksByName(String title, int pageno){
        Pageable pageable = PageRequest.of(pageno, 10);
        return bookRepository.findByTitle(title, pageable);
    }

    @Transactional
    public Optional<Books> updateBook(Books book) {
        Optional<Books> existingBook = bookRepository.findById(book.getId());

        if (existingBook.isPresent()) {
            Books bookToUpdate = existingBook.get();
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setIsbn(book.getIsbn());
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setPublishedDate(book.getPublishedDate());

            return Optional.of(bookRepository.save(bookToUpdate));
        } else {
            throw new BookNotFoundException("Book Not Found");
        }
    }

    public void deleteBook(int id){
        Optional<Books> deleteBook = bookRepository.findById(id);
        if(!deleteBook.isPresent()){
            throw new BookNotFoundException("Book Not Found");
        }
        Books myBook = deleteBook.get();
        bookRepository.delete(myBook);
    }
}
