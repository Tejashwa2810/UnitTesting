package com.example.Project1.Services;

import com.example.Project1.Controller.BookController;
import com.example.Project1.Exceptions.BookNotFoundException;
import com.example.Project1.Models.Author;
import com.example.Project1.Models.Books;
import com.example.Project1.Service.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Date;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceTest {

    @MockitoBean
    private BookService bookService;

    @Autowired
    private BookController bookController;

    @Test
    public void addTwoNumbersTest(){
//        int result = bookService.addTwoNumbers(12,35);
//
//        int expected = 47;
//
//        Assert.assertEquals(result, expected);

//        when(bookService.addTwoNumbers(2,4)).thenReturn(6);

        when(bookService.addTwoNumbers(anyInt(), anyInt())).thenReturn(6);

        int result = 6;

        Assert.assertEquals(result, bookController.addTwoNumbers(2,4));
    }


    @Test
    public void createBookTest(){
        Date date = new Date();

        Books book = new Books();
        book.setPublishedDate(date);
        book.setIsbn(111111L);
        book.setTitle("Harry Potter");
        book.setAuthor(new Author());

        when(bookController.createBook(book)).thenReturn(book);

        String str = "Harry Potter";

        Assert.assertEquals(str, bookController.createBook(book).getTitle());
    }

    @Test
    public void updateBook() {
        Date date = new Date();

        Books book = new Books();
        book.setId(1);
        book.setPublishedDate(date);
        book.setIsbn(111111L);
        book.setTitle("Harry Potter");
        book.setAuthor(new Author());

        // Mock the service
        when(bookService.updateBook(book)).thenThrow(new BookNotFoundException("Book Not Found"));

        // Call controller (which uses the mocked service)
        BookNotFoundException exception =
                assertThrows(BookNotFoundException.class, () -> bookController.updateBook(book));

        Assert.assertEquals("Book Not Found", exception.getMessage());
    }

    /* KEY Takeaways

    -> Never mock the controller itself if you’re testing the controller → always mock its dependency (service).

    -> Your error happened because the real controller was running, which hit an empty Optional in the repository.

    -> Fix = mock the service (bookService) and let controller delegate to it.

     */

}
