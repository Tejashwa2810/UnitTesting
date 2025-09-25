package com.example.Project1.Services;

import com.example.Project1.Controller.BookController;
import com.example.Project1.Service.BookService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

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

        when(bookService.addTwoNumbers(2,4)).thenReturn(6);

        int result = 6;

        Assert.assertEquals(result, bookController.addTwoNumbers(2,4));
    }
}
