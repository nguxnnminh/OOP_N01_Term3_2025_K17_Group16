package com.example.controller;

import com.example.model.BorrowRecord;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BorrowRecordController {

    private List<BorrowRecord> records = new ArrayList<>();

    public BorrowRecordController() {
        records.add(new BorrowRecord("BR1", "B1", "R1", "2025-06-01", "2025-06-15"));
        records.add(new BorrowRecord("BR2", "B2", "R2", "2025-06-02", ""));
        records.add(new BorrowRecord("BR3", "B3", "R3", "2025-06-03", ""));
    }

    @GetMapping("/borrow-records")
    public String showBorrowRecords(Model model) {
        model.addAttribute("records", records);
        model.addAttribute("record", new BorrowRecord("", "", "", "", ""));
        return "borrow-records";
    }

    @PostMapping("/borrow-records/add")
    public String addBorrowRecord(@ModelAttribute("record") BorrowRecord record, RedirectAttributes redirectAttributes) {
        if (record.getId() == null || record.getId().trim().isEmpty() ||
            record.getBookId() == null || record.getBookId().trim().isEmpty() ||
            record.getReaderId() == null || record.getReaderId().trim().isEmpty()) {
            redirectAttributes.addAttribute("error", "Vui lòng điền đầy đủ ID phiếu mượn, mã sách và mã độc giả");
            return "redirect:/borrow-records";
        }
        if (records.stream().anyMatch(r -> r.getId().equals(record.getId()))) {
            redirectAttributes.addAttribute("error", "ID phiếu mượn đã tồn tại");
            return "redirect:/borrow-records";
        }
        records.add(record);
        return "redirect:/borrow-records";
    }

    @GetMapping("/borrow-records/edit/{id}")
    public String showEditBorrowRecordForm(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        BorrowRecord record = records.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (record == null) {
            redirectAttributes.addAttribute("error", "Phiếu mượn không tồn tại");
            return "redirect:/borrow-records";
        }
        model.addAttribute("record", record);
        model.addAttribute("records", records);
        return "borrow-records";
    }

    @PostMapping("/borrow-records/update")
    public String updateBorrowRecord(@ModelAttribute("record") BorrowRecord updatedRecord, RedirectAttributes redirectAttributes) {
        if (updatedRecord.getId() == null || updatedRecord.getId().trim().isEmpty() ||
            updatedRecord.getBookId() == null || updatedRecord.getBookId().trim().isEmpty() ||
            updatedRecord.getReaderId() == null || updatedRecord.getReaderId().trim().isEmpty()) {
            redirectAttributes.addAttribute("error", "Vui lòng điền đầy đủ ID phiếu mượn, mã sách và mã độc giả");
            return "redirect:/borrow-records";
        }
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getId().equals(updatedRecord.getId())) {
                records.set(i, updatedRecord);
                break;
            }
        }
        return "redirect:/borrow-records";
    }

    @GetMapping("/borrow-records/delete/{id}")
    public String deleteBorrowRecord(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        boolean removed = records.removeIf(r -> r.getId().equals(id));
        if (!removed) {
            redirectAttributes.addAttribute("error", "Phiếu mượn không tồn tại");
        }
        return "redirect:/borrow-records";
    }
}