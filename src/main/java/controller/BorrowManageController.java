package controller;

import model.BorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.Library;

@Controller
public class BorrowManageController {
    @Autowired
    private Library library;

    @GetMapping("/add-borrow")
    public String showAddBorrowForm() {
        return "add-borrow";
    }

    @PostMapping("/add-borrow")
    public String addBorrow(@RequestParam("id") String id,
                           @RequestParam("bookId") String bookId,
                           @RequestParam("readerId") String readerId,
                           @RequestParam("borrowDate") String borrowDate,
                           @RequestParam("returnDate") String returnDate,
                           Model model) {
        try {
            BorrowRecord record = new BorrowRecord(id, bookId, readerId, borrowDate, returnDate);
            library.addBorrowRecord(record);
            model.addAttribute("message", "Thêm phiếu mượn thành công!");
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi thêm phiếu mượn: " + e.getMessage());
        }
        return "add-borrow";
    }

    @GetMapping("/update-borrow")
    public String showUpdateBorrowForm(@RequestParam("id") String id, Model model) {
        try {
            BorrowRecord borrow = library.getBorrowRecordById(id);
            model.addAttribute("borrow", borrow);
            return "update-borrow";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi lấy phiếu mượn: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/update-borrow")
    public String updateBorrow(@RequestParam("id") String id,
                              @RequestParam("borrowDate") String borrowDate,
                              @RequestParam("returnDate") String returnDate,
                              Model model) {
        try {
            BorrowRecord borrow = library.getBorrowRecordById(id);
            if (borrow != null) {
                borrow.setBorrowDate(borrowDate);
                borrow.setReturnDate(returnDate);
                library.updateBorrowRecord(borrow);
                model.addAttribute("message", "Cập nhật thành công!");
            } else {
                model.addAttribute("error", "Không tìm thấy phiếu mượn!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi cập nhật phiếu mượn: " + e.getMessage());
        }
        return "update-borrow";
    }

    @GetMapping("/delete-borrow")
    public String deleteBorrow(@RequestParam("id") String id, Model model) {
        try {
            if (library.deleteBorrowRecord(id)) {
                model.addAttribute("message", "Xóa phiếu mượn thành công!");
            } else {
                model.addAttribute("error", "Không tìm thấy phiếu mượn!");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi xóa phiếu mượn: " + e.getMessage());
        }
        return "redirect:/all-borrows";
    }
}