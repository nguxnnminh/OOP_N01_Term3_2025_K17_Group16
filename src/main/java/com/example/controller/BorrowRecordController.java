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

            Book book = bookRepo.findById(record.getBookId())
                    .orElseThrow(() -> new IllegalArgumentException("Sách không tồn tại"));

            // Tính số sách đang mượn
            long currentlyBorrowed = borrowRecordRepo.findByBookId(book.getId()).stream()
                    .filter(r -> r.getReturnDate() == null || r.getReturnDate().isBlank())
                    .count();

            if (currentlyBorrowed >= book.getTotalQuantity()) {
                throw new IllegalArgumentException("Sách đã hết, không thể mượn");
            }

            if (!readerRepo.existsById(record.getReaderId())) {
                throw new IllegalArgumentException("Độc giả không tồn tại");
            }

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

            BorrowRecord existingRecord = borrowRecordRepo.findById(record.getId())
                    .orElseThrow(() -> new IllegalArgumentException("Phiếu mượn không tồn tại"));

            if (!readerRepo.existsById(record.getReaderId())) {
                throw new IllegalArgumentException("Độc giả không tồn tại");
            }

            // Nếu đổi sang mã sách khác thì cần kiểm tra tồn kho
            if (!existingRecord.getBookId().equals(record.getBookId())) {
                Book newBook = bookRepo.findById(record.getBookId())
                        .orElseThrow(() -> new IllegalArgumentException("Sách mới không tồn tại"));

                long currentlyBorrowed = borrowRecordRepo.findByBookId(newBook.getId()).stream()
                        .filter(r -> r.getReturnDate() == null || r.getReturnDate().isBlank())
                        .count();

                if (currentlyBorrowed >= newBook.getTotalQuantity()) {
                    throw new IllegalArgumentException("Sách mới đã hết, không thể mượn");
                }
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
            borrowRecordRepo.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Phiếu mượn không tồn tại"));

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
