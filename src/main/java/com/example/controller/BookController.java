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
        try {
            books.add(new Book("B1", "Lập trình Java", "Nguyễn Văn A"));
            books.add(new Book("B2", "Cấu trúc dữ liệu", "Trần Thị B"));
            books.add(new Book("B3", "Thuật toán", "Lê Văn C"));
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo danh sách sách: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất khởi tạo BookController");
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        try {
            model.addAttribute("books", books);
            model.addAttribute("book", new Book("", "", ""));
            return "books";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị danh sách sách: " + e.getMessage());
            return "books";
        } finally {
            System.out.println("Hoàn tất hiển thị danh sách sách");
        }
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
        try {
            if (book.getId() == null || book.getId().trim().isEmpty() ||
                book.getTitle() == null || book.getTitle().trim().isEmpty() ||
                book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ thông tin sách");
            }
            if (books.stream().anyMatch(b -> b.getId().equals(book.getId()))) {
                throw new IllegalArgumentException("ID sách đã tồn tại");
            }
            books.add(book);
            redirectAttributes.addFlashAttribute("success", "Thêm sách thành công");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi thêm sách: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác thêm sách");
        }
        return "redirect:/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditBookForm(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Book book = books.stream()
                    .filter(b -> b.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Sách không tồn tại"));
            model.addAttribute("book", book);
            model.addAttribute("books", books);
            return "books";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/books";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi hiển thị form sửa sách: " + e.getMessage());
            return "redirect:/books";
        } finally {
            System.out.println("Hoàn tất thao tác hiển thị form sửa sách");
        }
    }

    @PostMapping("/books/update")
    public String updateBook(@ModelAttribute("book") Book updatedBook, RedirectAttributes redirectAttributes) {
        try {
            if (updatedBook.getId() == null || updatedBook.getId().trim().isEmpty() ||
                updatedBook.getTitle() == null || updatedBook.getTitle().trim().isEmpty() ||
                updatedBook.getAuthor() == null || updatedBook.getAuthor().trim().isEmpty()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ thông tin sách");
            }
            boolean found = false;
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getId().equals(updatedBook.getId())) {
                    books.set(i, updatedBook);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Sách không tồn tại");
            }
            redirectAttributes.addFlashAttribute("success", "Cập nhật sách thành công");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi cập nhật sách: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác cập nhật sách");
        }
        return "redirect:/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        try {
            boolean removed = books.removeIf(b -> b.getId().equals(id));
            if (!removed) {
                throw new IllegalArgumentException("Sách không tồn tại");
            }
            redirectAttributes.addFlashAttribute("success", "Xóa sách thành công");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xóa sách: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác xóa sách");
        }
        return "redirect:/books";
    }
}