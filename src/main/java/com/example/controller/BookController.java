package com.example.controller;

import com.example.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private List<Book> books = new ArrayList<>();

    public BookController() {
        books.add(new Book("B1", "Lập trình Java", "Nguyễn Văn A"));
        books.add(new Book("B2", "Cấu trúc dữ liệu", "Trần Thị B"));
        books.add(new Book("B3", "Thuật toán", "Lê Văn C"));
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        model.addAttribute("books", books);
        model.addAttribute("book", new Book("", "", ""));
        return "books";
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
        if (book.getId() == null || book.getId().trim().isEmpty() ||
            book.getTitle() == null || book.getTitle().trim().isEmpty() ||
            book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            redirectAttributes.addAttribute("error", "Vui lòng điền đầy đủ thông tin sách");
            return "redirect:/books";
        }
        if (books.stream().anyMatch(b -> b.getId().equals(book.getId()))) {
            redirectAttributes.addAttribute("error", "ID sách đã tồn tại");
            return "redirect:/books";
        }
        books.add(book);
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditBookForm(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        Book book = books.stream()
                .filter(b -> b.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (book == null) {
            redirectAttributes.addAttribute("error", "Sách không tồn tại");
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping("/books/update")
    public String updateBook(@ModelAttribute("book") Book updatedBook, RedirectAttributes redirectAttributes) {
        if (updatedBook.getId() == null || updatedBook.getId().trim().isEmpty() ||
            updatedBook.getTitle() == null || updatedBook.getTitle().trim().isEmpty() ||
            updatedBook.getAuthor() == null || updatedBook.getAuthor().trim().isEmpty()) {
            redirectAttributes.addAttribute("error", "Vui lòng điền đầy đủ thông tin sách");
            return "redirect:/books";
        }
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(updatedBook.getId())) {
                books.set(i, updatedBook);
                break;
            }
        }
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        boolean removed = books.removeIf(b -> b.getId().equals(id));
        if (!removed) {
            redirectAttributes.addAttribute("error", "Sách không tồn tại");
        }
        return "redirect:/books";
    }
}