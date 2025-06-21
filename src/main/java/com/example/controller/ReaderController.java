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
        try {
            readers.add(new Reader("R1", "Nguyễn Văn A", "123456789012", "0901234567", "1990-01-01"));
            readers.add(new Reader("R2", "Trần Thị B", "987654321098", "0912345678", "1992-02-02"));
            readers.add(new Reader("R3", "Lê Văn C", "456789123456", "0923456789", "1995-03-03"));
        } catch (Exception e) {
            System.err.println("Lỗi khi khởi tạo danh sách độc giả: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất khởi tạo ReaderController");
        }
    }

    @GetMapping("/readers")
    public String showReaders(Model model) {
        try {
            model.addAttribute("readers", readers);
            model.addAttribute("reader", new Reader("", "", "", "", ""));
            return "readers";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị danh sách độc giả: " + e.getMessage());
            return "readers";
        } finally {
            System.out.println("Hoàn tất hiển thị danh sách độc giả");
        }
    }

    @PostMapping("/readers/add")
    public String addReader(@ModelAttribute("reader") Reader reader, RedirectAttributes redirectAttributes) {
        try {
            if (reader.getId() == null || reader.getId().trim().isEmpty() ||
                reader.getName() == null || reader.getName().trim().isEmpty() ||
                reader.getCccd() == null || reader.getCccd().trim().isEmpty()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ mã độc giả, tên và CCCD");
            }
            if (readers.stream().anyMatch(r -> r.getId().equals(reader.getId()))) {
                throw new IllegalArgumentException("ID độc giả đã tồn tại");
            }
            readers.add(reader);
            redirectAttributes.addFlashAttribute("success", "Thêm độc giả thành công");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi thêm độc giả: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác thêm độc giả");
        }
        return "redirect:/readers";
    }

    @GetMapping("/readers/edit/{id}")
    public String showEditReaderForm(@PathVariable("id") String id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Reader reader = readers.stream()
                    .filter(r -> r.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Độc giả không tồn tại"));
            model.addAttribute("reader", reader);
            model.addAttribute("readers", readers);
            return "readers";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/readers";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi hiển thị form sửa độc giả: " + e.getMessage());
            return "redirect:/readers";
        } finally {
            System.out.println("Hoàn tất thao tác hiển thị form sửa độc giả");
        }
    }

    @PostMapping("/readers/update")
    public String updateReader(@ModelAttribute("reader") Reader updatedReader, RedirectAttributes redirectAttributes) {
        try {
            if (updatedReader.getId() == null || updatedReader.getId().trim().isEmpty() ||
                updatedReader.getName() == null || updatedReader.getName().trim().isEmpty() ||
                updatedReader.getCccd() == null || updatedReader.getCccd().trim().isEmpty()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ mã độc giả, tên và CCCD");
            }
            boolean found = false;
            for (int i = 0; i < readers.size(); i++) {
                if (readers.get(i).getId().equals(updatedReader.getId())) {
                    readers.set(i, updatedReader);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalArgumentException("Độc giả không tồn tại");
            }
            redirectAttributes.addFlashAttribute("success", "Cập nhật độc giả thành công");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi cập nhật độc giả: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác cập nhật độc giả");
        }
        return "redirect:/readers";
    }

    @GetMapping("/readers/delete/{id}")
    public String deleteReader(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
        try {
            boolean removed = readers.removeIf(r -> r.getId().equals(id));
            if (!removed) {
                throw new IllegalArgumentException("Độc giả không tồn tại");
            }
            redirectAttributes.addFlashAttribute("success", "Xóa độc giả thành công");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xóa độc giả: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác xóa độc giả");
        }
        return "redirect:/readers";
    }
}