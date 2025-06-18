package controller;

import model.BorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import service.Library;

@Controller
public class BorrowDetailController {
    @Autowired
    private Library library;

    @GetMapping("/borrow-detail")
    public String getBorrowDetail(@RequestParam("id") String id, Model model) {
        try {
            BorrowRecord borrow = library.getBorrowRecordById(id);
            model.addAttribute("borrow", borrow);
            return "borrow-detail";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi lấy chi tiết phiếu mượn: " + e.getMessage());
            return "error";
        }
    }
}