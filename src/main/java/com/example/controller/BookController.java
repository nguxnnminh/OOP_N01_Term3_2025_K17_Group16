package com.example.controller;

import com.example.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @GetMapping("/")
    public String showHomePage() {
    return "index";
    }
    
    @GetMapping("/books")
    public String showBooks(Model model) {
        List<Book> books = new ArrayList<>();
        try {
            books.add(new Book("B1", "Lập trình Java", "Nguyễn Văn A"));
            books.add(new Book("B2", "Cấu trúc dữ liệu", "Trần Thị B"));
            books.add(new Book("B3", "Thuật toán", "Lê Văn C"));
        } catch (IllegalArgumentException e) {
        System.err.println("Lỗi khi tạo danh sách sách: " + e.getMessage());
        }

        model.addAttribute("books", books);
        return "books";
    }
}