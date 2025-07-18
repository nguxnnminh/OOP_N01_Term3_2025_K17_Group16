package com.example.controller;

import com.example.model.Book;
import com.example.model.BorrowRecord;
import com.example.repository.BookRepository;
import com.example.repository.BorrowRecordRepository;
import com.example.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private ReaderRepository readerRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        try {
            List<Book> books = bookRepository.findAll();
            long totalBooks = books.stream().mapToLong(Book::getTotalQuantity).sum();

            long totalBorrowRecords = borrowRecordRepository.count();  // Số sách đang mượn
            long totalReaders = readerRepository.count();

            long borrowedBooks = totalBorrowRecords;
            long availableBooks = totalBooks - borrowedBooks;

            List<Map<String, Object>> bookStats = new ArrayList<>();

            for (Book book : books) {
                long currentlyBorrowed = borrowRecordRepository.findByBookId(book.getId()).size();
                long available = book.getTotalQuantity() - currentlyBorrowed;

                Map<String, Object> stat = new HashMap<>();
                stat.put("id", book.getId());
                stat.put("title", book.getTitle());
                stat.put("total", book.getTotalQuantity());
                stat.put("borrowed", currentlyBorrowed);
                stat.put("available", available);

                bookStats.add(stat);
            }

            model.addAttribute("totalBooks", totalBooks);
            model.addAttribute("borrowedBooks", borrowedBooks);
            model.addAttribute("availableBooks", availableBooks);
            model.addAttribute("totalBorrowRecords", totalBorrowRecords);
            model.addAttribute("totalReaders", totalReaders);
            model.addAttribute("bookStats", bookStats);

            return "index";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị trang chủ: " + e.getMessage());
            return "index";
        } finally {
            System.out.println("Hoàn tất hiển thị trang chủ");
        }
    }

    @GetMapping("/books")
    public String showBooks(Model model, @RequestParam(value = "searchTitle", required = false) String searchTitle) {
        try {
            if (searchTitle == null) searchTitle = "";

            List<Book> filteredBooks;
            if (!searchTitle.trim().isEmpty()) {
                filteredBooks = bookRepository.findByTitleContainingIgnoreCase(searchTitle.trim());
            } else {
                filteredBooks = bookRepository.findAll();
            }

            model.addAttribute("books", filteredBooks);
            model.addAttribute("searchTitle", searchTitle);
            model.addAttribute("book", new Book());
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

            if (book.getTotalQuantity() < 0) {
                throw new IllegalArgumentException("Tổng số lượng sách phải >= 0");
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

            if (updatedBook.getTotalQuantity() < 0) {
                throw new IllegalArgumentException("Tổng số lượng sách phải >= 0");
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

            List<BorrowRecord> borrowRecords = borrowRecordRepository.findByBookId(id).stream()
                    .filter(r -> r.getReturnDate() == null || r.getReturnDate().isBlank())
                    .collect(Collectors.toList());

            if (!borrowRecords.isEmpty()) {
                throw new IllegalArgumentException("Sách đang được mượn, không thể xóa");
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