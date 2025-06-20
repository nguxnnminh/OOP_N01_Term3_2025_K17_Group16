package com.example.controller;

import com.example.model.Reader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReaderController {

    private List<Reader> readers = new ArrayList<>();

    public ReaderController() {
        readers.add(new Reader("R1", "Nguyễn Văn A", "123456789012", "0901234567", "1990-01-01"));
        readers.add(new Reader("R2", "Trần Thị B", "987654321098", "0912345678", "1992-02-02"));
        readers.add(new Reader("R3", "Lê Văn C", "456789123456", "0923456789", "1995-03-03"));
    }

    @GetMapping("/readers")
    public String showReaders(Model model) {
        model.addAttribute("readers", readers);
        model.addAttribute("reader", new Reader("", "", "", "", ""));
        return "readers";
    }

    @PostMapping("/readers/add")
    public String addReader(@ModelAttribute("reader") Reader reader, RedirectAttributes redirectAttributes) {
        if (reader.getId() == null || reader.getId().trim().isEmpty() ||
            reader.getName() == null || reader.getName().trim().isEmpty() ||
            reader.getCccd() == null || reader.getCccd().trim().isEmpty()) {
            redirectAttributes.addAttribute("error", "Vui lòng điền đầy đủ mã độc giả, tên và CCCD");
            return "redirect:/readers";
        }
        if (readers.stream().anyMatch(r -> r.getId().equals(reader.getId()))) {
            redirectAttributes.addAttribute("error", "ID độc giả đã tồn tại");
            return "redirect:/readers";
        }
        readers.add(reader);
        return "redirect:/readers";
    }

    @GetMapping("/readers/edit/{id}")
    public String showEditReaderForm(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        Reader reader = readers.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (reader == null) {
            redirectAttributes.addAttribute("error", "Độc giả không tồn tại");
            return "redirect:/readers";
        }
        model.addAttribute("reader", reader);
        model.addAttribute("readers", readers);
        return "readers";
    }

    @PostMapping("/readers/update")
    public String updateReader(@ModelAttribute("reader") Reader updatedReader, RedirectAttributes redirectAttributes) {
        if (updatedReader.getId() == null || updatedReader.getId().trim().isEmpty() ||
            updatedReader.getName() == null || updatedReader.getName().trim().isEmpty() ||
            updatedReader.getCccd() == null || updatedReader.getCccd().trim().isEmpty()) {
            redirectAttributes.addAttribute("error", "Vui lòng điền đầy đủ mã độc giả, tên và CCCD");
            return "redirect:/readers";
        }
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i).getId().equals(updatedReader.getId())) {
                readers.set(i, updatedReader);
                break;
            }
        }
        return "redirect:/readers";
    }

    @GetMapping("/readers/delete/{id}")
    public String deleteReader(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        boolean removed = readers.removeIf(r -> r.getId().equals(id));
        if (!removed) {
            redirectAttributes.addAttribute("error", "Độc giả không tồn tại");
        }
        return "redirect:/readers";
    }
}