package controller;

import model.BorrowRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.Library;

import java.util.List;

@Controller
public class BorrowRecordController {
    @Autowired
    private Library library;

    @GetMapping("/all-borrows")
    public String getAllBorrowRecords(Model model) {
        try {
            List<BorrowRecord> borrows = library.getAllBorrowRecords();
            model.addAttribute("borrows", borrows);
            return "all-borrows";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi lấy danh sách phiếu mượn: " + e.getMessage());
            return "error";
        }
    }
}