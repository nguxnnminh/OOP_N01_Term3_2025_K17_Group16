package com.example.controller;

import com.example.model.Reader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReaderController {

    @GetMapping("/readers")
    public String showReaders(Model model) {
        List<Reader> readers = new ArrayList<>();
        try {
            readers.add(new Reader("R1", "Nguyễn Văn A", "123456789012", "0901234567", "1990-01-01"));
            readers.add(new Reader("R2", "Trần Thị B", "987654321098", "0912345678", "1992-02-02"));
            readers.add(new Reader("R3", "Lê Văn C", "456789123456", "0923456789", "1995-03-03"));
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi khi tạo danh sách độc giả: " + e.getMessage());
        }

    model.addAttribute("readers", readers);
    return "readers";
    }
}