package com.example.controller;

import com.example.model.Book;
import com.example.model.BorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BorrowRecordController {

    private List<BorrowRecord> records = new ArrayList<>();

    @Autowired
    private BookController bookController;

    public BorrowRecordController() {
        try {
            records.add(new BorrowRecord("BR1", "B1", "R1", "2025-06-01", "2025-06-15"));
            records.add(new BorrowRecord("BR2", "B2", "R2", "2025-06-02", ""));
            records.add(new BorrowRecord("BR3", "B3", "R3", "2025-06-03", ""));
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo danh sách phiếu mượn: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất khởi tạo BorrowRecordController");
        }
    }

    @GetMapping("/borrow-records")
    public String showBorrowRecords(Model model) {
        try {
            model.addAttribute("records", records);
            model.addAttribute("record", new BorrowRecord("", "", "", "", ""));
            return "borrow-records";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị danh sách phiếu mượn: " + e.getMessage());
            return "borrow-records";
        } finally {
            System.out.println("Hoàn tất hiển thị danh sách phiếu mượn");
        }
    }

    @PostMapping("/borrow-records/add")
    public String addBorrowRecord(@ModelAttribute("record") BorrowRecord record, RedirectAttributes redirectAttributes) {
        try {
            if (record.getId() == null || record.getId().trim().isEmpty() ||
                record.getBookId() == null || record.getBookId().trim().isEmpty() ||
                record.getReaderId() == null || record.getReaderId().trim().isEmpty()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ ID phiếu mượn, mã sách và mã độc giả");
            }

            if (records.stream().anyMatch(r -> r.getId().equals(record.getId()))) {
                throw new IllegalArgumentException("ID phiếu mượn đã tồn tại");
            }

            List<Book> books = bookController.getBooks();
            boolean bookExists = books.stream().anyMatch(b -> b.getId().equals(record.getBookId()));
            if (!bookExists) {
                throw new IllegalArgumentException("Sách không tồn tại trong thư viện");
            }

            boolean isBookBorrowed = records.stream()
                    .anyMatch(r -> r.getBookId().equals(record.getBookId()) && (r.getReturnDate() == null || r.getReturnDate().trim().isEmpty()));
            if (isBookBorrowed) {
                throw new IllegalArgumentException("Sách hiện đang được mượn");
            }

            records.add(record);
            redirectAttributes.addFlashAttribute("success", "Thêm phiếu mượn thành công");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi thêm phiếu mượn: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác thêm phiếu mượn");
        }
        return "redirect:/rl-records";
    }

    @GetMapping("/borrow-records/edit/{id}")
    public String showEditBorrowRecordForm(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            BorrowRecord record = records.stream()
                    .filter(r -> r.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Phiếu mượn không tồn tại"));
            model.addAttribute("record", record);
            model.addAttribute("records", records);
            return "borrow-records";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/borrow-records";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi hiển thị form sửa phiếu mượn: " + e.getMessage());
            return "redirect:/borrow-records";
        } finally {
            System.out.println("Hoàn tất thao tác hiển thị form sửa phiếu mượn");
        }
    }

    @PostMapping("/borrow-records/update")
    public String updateBorrowRecord(@ModelAttribute("record") BorrowRecord updatedRecord, RedirectAttributes redirectAttributes) {
        try {
            if (updatedRecord.getId() == null || updatedRecord.getId().trim().isEmpty() ||
                updatedRecord.getBookId() == null || updatedRecord.getBookId().trim().isEmpty() ||
                updatedRecord.getReaderId() == null || updatedRecord.getReaderId().trim().isEmpty()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ ID phiếu mượn, mã sách và mã độc giả");
            }

            List<Book> books = bookController.getBooks();
            boolean bookExists = books.stream().anyMatch(b -> b.getId().equals(updatedRecord.getBookId()));
            if (!bookExists) {
                throw new IllegalArgumentException("Sách không tồn tại trong thư viện");
            }

            boolean isBookBorrowed = records.stream()
                    .anyMatch(r -> !r.getId().equals(updatedRecord.getId()) &&
                            r.getBookId().equals(updatedRecord.getBookId()) &&
                            (r.getReturnDate() == null || r.getReturnDate().trim().isEmpty()));
            if (isBookBorrowed) {
                throw new IllegalArgumentException("Sách hiện đang được mượn bởi phiếu mượn khác");
            }

            boolean found = false;
            for (int i = 0; i < records.size(); i++) {
                if (records.get(i).getId().equals(updatedRecord.getId())) {
                    records.set(i, updatedRecord);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Phiếu mượn không tồn tại");
            }
            redirectAttributes.addFlashAttribute("success", "Cập nhật phiếu mượn thành công");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi cập nhật phiếu mượn: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác cập nhật phiếu mượn");
        }
        return "redirect:/borrow-records";
    }

    @GetMapping("/borrow-records/delete/{id}")
    public String deleteBorrowRecord(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        try {
            boolean removed = records.removeIf(r -> r.getId().equals(id));
            if (!removed) {
                throw new IllegalArgumentException("Phiếu mượn không tồn tại");
            }
            redirectAttributes.addFlashAttribute("success", "Xóa phiếu mượn thành công");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xóa phiếu mượn: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác xóa phiếu mượn");
        }
        return "redirect:/borrow-records";
    }
}