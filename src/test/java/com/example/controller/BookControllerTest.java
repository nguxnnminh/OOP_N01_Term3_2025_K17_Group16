package com.example.controller;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookControllerTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testShowBooks() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(new Book("1", "Title", "Author", "Genre")));
        String view = bookController.showBooks(model, null);
        assertEquals("books", view);
        verify(model).addAttribute(eq("books"), anyList());
    }

    @Test
    public void testAddBook_Valid() {
        Book book = new Book("1", "Title", "Author", "Genre");
        when(bookRepository.existsById("1")).thenReturn(false);
        String view = bookController.addBook(book, redirectAttributes);
        assertEquals("redirect:/books", view);
        verify(redirectAttributes).addFlashAttribute(eq("success"), eq("Thêm sách thành công"));
    }

    @Test
    public void testAddBook_MissingFields() {
        Book book = new Book("", "", "", "");
        String view = bookController.addBook(book, redirectAttributes);
        assertEquals("redirect:/books", view);
        verify(redirectAttributes).addFlashAttribute(eq("error"), contains("Vui lòng điền đầy đủ thông tin"));
    }

    @Test
    public void testAddBook_DuplicateId() {
        Book book = new Book("1", "Title", "Author", "Genre");
        when(bookRepository.existsById("1")).thenReturn(true);
        String view = bookController.addBook(book, redirectAttributes);
        assertEquals("redirect:/books", view);
        verify(redirectAttributes).addFlashAttribute(eq("error"), contains("ID sách đã tồn tại"));
    }

    @Test
    public void testShowEditBookForm_ValidId() {
        Book book = new Book("1", "Title", "Author", "Genre");
        when(bookRepository.findById("1")).thenReturn(Optional.of(book));
        when(bookRepository.findAll()).thenReturn(Collections.singletonList(book));

        String view = bookController.showEditBookForm("1", model, redirectAttributes);
        assertEquals("books", view);
        verify(model).addAttribute(eq("book"), eq(book));
    }

    @Test
    public void testShowEditBookForm_InvalidId() {
        when(bookRepository.findById("99")).thenReturn(Optional.empty());
        String view = bookController.showEditBookForm("99", model, redirectAttributes);
        assertEquals("redirect:/books", view);
        verify(redirectAttributes).addFlashAttribute(eq("error"), contains("Sách không tồn tại"));
    }

    @Test
    public void testUpdateBook_Valid() {
        Book book = new Book("1", "Title", "Author", "Genre");

        when(bookRepository.existsById("1")).thenReturn(true);
        when(bookRepository.findById("1")).thenReturn(Optional.of(book));

        String view = bookController.updateBook(book, redirectAttributes);
        assertEquals("redirect:/books", view);
        verify(redirectAttributes).addFlashAttribute(eq("success"), eq("Cập nhật sách thành công"));
    }

    @Test
    public void testUpdateBook_InvalidId() {
        Book book = new Book("99", "Title", "Author", "Genre");
        when(bookRepository.existsById("99")).thenReturn(false);

        String view = bookController.updateBook(book, redirectAttributes);
        assertEquals("redirect:/books", view);
        verify(redirectAttributes).addFlashAttribute(eq("error"), eq("Sách không tồn tại"));
    }

    @Test
    public void testDeleteBook_ValidId() {
        when(bookRepository.existsById("1")).thenReturn(true);
        String view = bookController.deleteBook("1", redirectAttributes);
        assertEquals("redirect:/books", view);
        verify(redirectAttributes).addFlashAttribute(eq("success"), eq("Xóa sách thành công"));
    }

    @Test
    public void testDeleteBook_InvalidId() {
        when(bookRepository.existsById("99")).thenReturn(false);
        String view = bookController.deleteBook("99", redirectAttributes);
        assertEquals("redirect:/books", view);
        verify(redirectAttributes).addFlashAttribute(eq("error"), eq("Sách không tồn tại"));
    }
}
