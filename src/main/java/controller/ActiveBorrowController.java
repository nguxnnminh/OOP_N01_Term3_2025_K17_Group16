package controller;

import model.BorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.Library;

import java.util.List;

@Controller
public class ActiveBorrowController {
    @Autowired
    private Library library;

    @GetMapping("/active-borrows")
    public String getActiveBorrowRecords(Model model) {
        try {
            List<BorrowRecord> activeBorrows = library.getActiveBorrowRecords();
            model.addAttribute("borrows", activeBorrows);
            return "active-borrows";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi lấy danh sách phiếu mượn còn hiệu lực: " + e.getMessage());
            return "error";
        }
    }
}