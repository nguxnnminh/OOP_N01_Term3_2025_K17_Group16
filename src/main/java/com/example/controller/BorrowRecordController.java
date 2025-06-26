package com.example.controller;

import com.example.model.BorrowRecord;
import com.example.model.Book;
import com.example.repository.BookRepository;
import com.example.repository.BorrowRecordRepository;
import com.example.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class BorrowRecordController {

    @Autowired
    private BorrowRecordRepository borrowRecordRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private ReaderRepository readerRepo;

    @GetMapping("/borrow-records")
    public String showBorrowRecords(Model model,
                                    @RequestParam(value = "searchQuery", required = false) String searchQuery) {
        try {
            List<BorrowRecord> records = borrowRecordRepo.findAll();

            if (searchQuery != null && !searchQuery.trim().isEmpty()) {
                String query = searchQuery.trim().toLowerCase();
                records = records.stream()
                        .filter(r -> r.getReaderId().toLowerCase().contains(query))
                        .collect(Collectors.toList());
            }

            model.addAttribute("records", records);
            model.addAttribute("record", new BorrowRecord("", "", "", "", ""));
            model.addAttribute("searchQuery", searchQuery);
            return "borrow-records";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị danh sách phiếu mượn: " + e.getMessage());
            return "borrow-records";
        } finally {
            System.out.println("Hoàn tất hiển thị danh sách phiếu mượn");
        }
    }

    @PostMapping("/borrow-records/add")
    public String addBorrowRecord(@ModelAttribute("record") BorrowRecord record,
                                  RedirectAttributes redirectAttributes) {
        try {
            if (record.getId().isBlank() || record.getBookId().isBlank() || record.getReaderId().isBlank()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ ID phiếu mượn, mã sách và mã sinh viên");
            }

            if (borrowRecordRepo.existsById(record.getId())) {
                throw new IllegalArgumentException("ID phiếu mượn đã tồn tại");
            }

            Optional<Book> bookOptional = bookRepo.findById(record.getBookId());
            if (!bookOptional.isPresent()) {
                throw new IllegalArgumentException("Sách không tồn tại trong thư viện");
            }

            Book book = bookOptional.get();
            if (book.isBorrowed()) {
                throw new IllegalArgumentException("Sách hiện đang được mượn");
            }

            if (!readerRepo.existsById(record.getReaderId())) {
                throw new IllegalArgumentException("Độc giả không tồn tại trong thư viện");
            }

            // Cập nhật trạng thái sách thành đã mượn
            book.setBorrowed(true);
            bookRepo.save(book);

            borrowRecordRepo.save(record);
            redirectAttributes.addFlashAttribute("success", "Thêm phiếu mượn thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi thêm phiếu mượn: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác thêm phiếu mượn");
        }
        return "redirect:/borrow-records";
    }

    @GetMapping("/borrow-records/edit/{id}")
    public String showEditBorrowRecordForm(@PathVariable("id") String id,
                                           Model model,
                                           RedirectAttributes redirectAttributes) {
        try {
            BorrowRecord record = borrowRecordRepo.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Phiếu mượn không tồn tại"));
            model.addAttribute("record", record);
            model.addAttribute("records", borrowRecordRepo.findAll());
            return "borrow-records";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi hiển thị form sửa phiếu mượn: " + e.getMessage());
            return "redirect:/borrow-records";
        } finally {
            System.out.println("Hoàn tất thao tác hiển thị form sửa phiếu mượn");
        }
    }

    @PostMapping("/borrow-records/update")
    public String updateBorrowRecord(@ModelAttribute("record") BorrowRecord record,
                                     RedirectAttributes redirectAttributes) {
        try {
            if (record.getId().isBlank() || record.getBookId().isBlank() || record.getReaderId().isBlank()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ ID phiếu mượn, mã sách và mã sinh viên");
            }

            if (!borrowRecordRepo.existsById(record.getId())) {
                throw new IllegalArgumentException("Phiếu mượn không tồn tại");
            }

            Optional<Book> bookOptional = bookRepo.findById(record.getBookId());
            if (!bookOptional.isPresent()) {
                throw new IllegalArgumentException("Sách không tồn tại");
            }

            Book book = bookOptional.get();
            Optional<BorrowRecord> existingRecord = borrowRecordRepo.findById(record.getId());
            if (!existingRecord.get().getBookId().equals(record.getBookId())) {
                // Nếu đổi sang sách khác, kiểm tra sách mới
                if (book.isBorrowed()) {
                    throw new IllegalArgumentException("Sách hiện đang được mượn");
                }
                // Cập nhật trạng thái sách cũ thành chưa mượn
                Optional<Book> oldBookOptional = bookRepo.findById(existingRecord.get().getBookId());
                if (oldBookOptional.isPresent()) {
                    Book oldBook = oldBookOptional.get();
                    oldBook.setBorrowed(false);
                    bookRepo.save(oldBook);
                }
                // Cập nhật trạng thái sách mới thành đã mượn
                book.setBorrowed(true);
                bookRepo.save(book);
            }

            if (!readerRepo.existsById(record.getReaderId())) {
                throw new IllegalArgumentException("Độc giả không tồn tại");
            }

            // Cập nhật trạng thái trả sách
            if (record.getReturnDate() != null && !record.getReturnDate().isBlank() &&
                (existingRecord.get().getReturnDate() == null || existingRecord.get().getReturnDate().isBlank())) {
                book.setBorrowed(false);
                bookRepo.save(book);
            } else if ((record.getReturnDate() == null || record.getReturnDate().isBlank()) &&
                      existingRecord.get().getReturnDate() != null && !existingRecord.get().getReturnDate().isBlank()) {
                if (book.isBorrowed()) {
                    throw new IllegalArgumentException("Sách hiện đang được mượn");
                }
                book.setBorrowed(true);
                bookRepo.save(book);
            }

            borrowRecordRepo.save(record);
            redirectAttributes.addFlashAttribute("success", "Cập nhật phiếu mượn thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi cập nhật phiếu mượn: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác cập nhật phiếu mượn");
        }
        return "redirect:/borrow-records";
    }

    @GetMapping("/borrow-records/delete/{id}")
    public String deleteBorrowRecord(@PathVariable("id") String id,
                                     RedirectAttributes redirectAttributes) {
        try {
            if (!borrowRecordRepo.existsById(id)) {
                throw new IllegalArgumentException("Phiếu mượn không tồn tại");
            }

            Optional<BorrowRecord> record = borrowRecordRepo.findById(id);
            if (record.isPresent() && (record.get().getReturnDate() == null || record.get().getReturnDate().isBlank())) {
                Optional<Book> book = bookRepo.findById(record.get().getBookId());
                if (book.isPresent()) {
                    Book b = book.get();
                    b.setBorrowed(false);
                    bookRepo.save(b);
                }
            }

            borrowRecordRepo.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Xóa phiếu mượn thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xóa phiếu mượn: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác xóa phiếu mượn");
        }
        return "redirect:/borrow-records";
    }
}