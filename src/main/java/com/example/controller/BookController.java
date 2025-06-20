package com.example.controller;

import com.example.model.Book;
import com.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/books")
    public String showBooks(Model model, @RequestParam(value = "searchTitle", required = false) String searchTitle) {
        try {
            List<Book> filteredBooks;
            if (searchTitle != null && !searchTitle.trim().isEmpty()) {
                filteredBooks = bookRepository.findByTitleContainingIgnoreCase(searchTitle.trim());
            } else {
                filteredBooks = bookRepository.findAll();
            }
            model.addAttribute("books", filteredBooks);
            model.addAttribute("searchTitle", searchTitle);
            model.addAttribute("book", new Book("", "", "", ""));
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
                book.getAuthor() == null || book.getAuthor().trim().isEmpty() ||
                book.getGenre() == null || book.getGenre().trim().isEmpty()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ thông tin sách");
            }

            if (bookRepository.existsById(book.getId())) {
                throw new IllegalArgumentException("ID sách đã tồn tại");
            }

            bookRepository.save(book);
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
            Optional<Book> optionalBook = bookRepository.findById(id);
            if (!optionalBook.isPresent()) {
                throw new IllegalArgumentException("Sách không tồn tại");
            }
            model.addAttribute("book", optionalBook.get());
            model.addAttribute("books", bookRepository.findAll());
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
                updatedBook.getAuthor() == null || updatedBook.getAuthor().trim().isEmpty() ||
                updatedBook.getGenre() == null || updatedBook.getGenre().trim().isEmpty()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ thông tin sách");
            }

            if (!bookRepository.existsById(updatedBook.getId())) {
                throw new IllegalArgumentException("Sách không tồn tại");
            }

            bookRepository.save(updatedBook);
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
            if (!bookRepository.existsById(id)) {
                throw new IllegalArgumentException("Sách không tồn tại");
            }

            bookRepository.deleteById(id);
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
