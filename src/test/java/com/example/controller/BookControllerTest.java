package com.example.controller;

import com.example.model.Book;
import com.example.model.BorrowRecord;
import com.example.repository.BookRepository;
import com.example.repository.BorrowRecordRepository;
import com.example.repository.ReaderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowRecordRepository borrowRecordRepository;

    @Mock
    private ReaderRepository readerRepository;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .setViewResolvers(viewResolver)
                .build();
    }

    // -------- Test Trang Chủ ----------
    @Test
    public void testShowHomePage() throws Exception {
        Book book = new Book("B1", "Java Basics", "John Doe", "Programming");
        book.setTotalQuantity(10);

        when(bookRepository.findAll()).thenReturn(List.of(book));
        when(borrowRecordRepository.count()).thenReturn(1L);
        when(readerRepository.count()).thenReturn(1L);
        when(borrowRecordRepository.findByBookId("B1")).thenReturn(List.of(new BorrowRecord()));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("totalBooks"))
                .andExpect(model().attributeExists("borrowedBooks"))
                .andExpect(model().attributeExists("availableBooks"))
                .andExpect(model().attributeExists("totalBorrowRecords"))
                .andExpect(model().attributeExists("totalReaders"))
                .andExpect(model().attributeExists("bookStats"));
    }

    // -------- Test Danh Sách Sách ----------
    @Test
    public void testShowBooks_NoSearch() throws Exception {
        when(bookRepository.findAll()).thenReturn(List.of(new Book()));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("books", "searchTitle", "book"));
    }

    @Test
    public void testShowBooks_WithSearch() throws Exception {
        when(bookRepository.findByTitleContainingIgnoreCase("Java")).thenReturn(List.of(new Book()));

        mockMvc.perform(get("/books").param("searchTitle", "Java"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("books", "searchTitle", "book"));
    }

    // -------- Test Thêm Sách ----------
    @Test
    public void testAddBook_Valid() throws Exception {
        when(bookRepository.existsById("B1")).thenReturn(false);

        mockMvc.perform(post("/books/add")
                        .param("id", "B1")
                        .param("title", "Java Basics")
                        .param("author", "John Doe")
                        .param("genre", "Programming")
                        .param("totalQuantity", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attribute("success", "Thêm sách thành công"));
    }

    @Test
    public void testAddBook_ExistId() throws Exception {
        when(bookRepository.existsById("B1")).thenReturn(true);

        mockMvc.perform(post("/books/add")
                        .param("id", "B1")
                        .param("title", "Java Basics")
                        .param("author", "John Doe")
                        .param("genre", "Programming")
                        .param("totalQuantity", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attributeExists("error"));
    }

    @Test
    public void testAddBook_MissingData() throws Exception {
        mockMvc.perform(post("/books/add")
                        .param("id", "")
                        .param("title", "")
                        .param("author", "")
                        .param("genre", "")
                        .param("totalQuantity", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attributeExists("error"));
    }

    // -------- Test Sửa Sách ----------
    @Test
    public void testShowEditBookForm_Valid() throws Exception {
        Book book = new Book("B1", "Java Basics", "John Doe", "Programming");
        book.setTotalQuantity(10);

        when(bookRepository.findById("B1")).thenReturn(Optional.of(book));
        when(bookRepository.findAll()).thenReturn(List.of(book));

        mockMvc.perform(get("/books/edit/B1"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("book", "books"));
    }

    @Test
    public void testShowEditBookForm_NotExist() throws Exception {
        when(bookRepository.findById("999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/books/edit/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attributeExists("error"));
    }

    @Test
    public void testUpdateBook_Valid() throws Exception {
        when(bookRepository.existsById("B1")).thenReturn(true);

        mockMvc.perform(post("/books/update")
                        .param("id", "B1")
                        .param("title", "Updated")
                        .param("author", "New Author")
                        .param("genre", "New Genre")
                        .param("totalQuantity", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attribute("success", "Cập nhật sách thành công"));
    }

    @Test
    public void testUpdateBook_MissingData() throws Exception {
        when(bookRepository.existsById("B1")).thenReturn(true);

        mockMvc.perform(post("/books/update")
                        .param("id", "B1")
                        .param("title", "")
                        .param("author", "")
                        .param("genre", "")
                        .param("totalQuantity", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attributeExists("error"));
    }

    // -------- Test Xóa Sách ----------
    @Test
    public void testDeleteBook_Valid() throws Exception {
        when(bookRepository.existsById("B1")).thenReturn(true);
        when(borrowRecordRepository.findByBookId("B1")).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/books/delete/B1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attribute("success", "Xóa sách thành công"));
    }

    @Test
    public void testDeleteBook_NotExist() throws Exception {
        when(bookRepository.existsById("B1")).thenReturn(false);

        mockMvc.perform(get("/books/delete/B1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attributeExists("error"));
    }

    @Test
    public void testDeleteBook_BookBorrowed() throws Exception {
        when(bookRepository.existsById("B1")).thenReturn(true);
        BorrowRecord record = new BorrowRecord("R1", "B1", "S1", "2024-06-01", null);
        when(borrowRecordRepository.findByBookId("B1")).thenReturn(List.of(record));

        mockMvc.perform(get("/books/delete/B1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attributeExists("error"));
    }
}
