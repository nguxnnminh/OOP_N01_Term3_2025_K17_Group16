package com.example.controller;

import com.example.model.BorrowRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BorrowRecordController {

    @GetMapping("/borrow-records")
    public String showBorrowRecords(Model model) {
        List<BorrowRecord> records = new ArrayList<>();
        records.add(new BorrowRecord("1", "B1", "R1", "2023-01-01", "2023-01-10"));
        records.add(new BorrowRecord("2", "B2", "R2", "2023-01-05", "2023-01-15"));
        records.add(new BorrowRecord("3", "B3", "R3", "2023-02-01", "2023-02-10"));

        model.addAttribute("records", records);
        return "borrow-records";
    }
    public String redirectToBorrowRecords() {
        return "redirect:/borrow-records";
    }
    public String showHomePage() {
        return "index";
    }
}
