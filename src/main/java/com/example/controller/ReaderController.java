package com.example.controller;

import com.example.model.Reader;
import com.example.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ReaderController {

    @Autowired
    private ReaderRepository readerRepo;

    @GetMapping("/readers")
    public String showReaders(Model model,
                              @RequestParam(value = "searchName", required = false) String searchName) {
        try {
            List<Reader> filteredReaders;
            if (searchName != null && !searchName.trim().isEmpty()) {
                filteredReaders = readerRepo.findByNameContainingIgnoreCase(searchName.trim());
            } else {
                filteredReaders = readerRepo.findAll();
            }

            model.addAttribute("readers", filteredReaders);
            model.addAttribute("searchName", searchName);

            if (!model.containsAttribute("reader")) {
                model.addAttribute("reader", new Reader("", "", "", "", ""));
            }

            return "readers";
        } catch (Exception e) {
            model.addAttribute("error", "Lỗi khi hiển thị danh sách độc giả: " + e.getMessage());
            return "readers";
        } finally {
            System.out.println("Hoàn tất hiển thị danh sách độc giả");
        }
    }

    @PostMapping("/readers/add")
    public String addReader(@ModelAttribute("reader") Reader reader,
                            RedirectAttributes redirectAttributes) {
        try {
            if (reader.getId().isBlank() || reader.getName().isBlank() || reader.getCccd().isBlank()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ mã độc giả, tên và CCCD");
            }

            if (readerRepo.existsById(reader.getId())) {
                throw new IllegalArgumentException("ID độc giả đã tồn tại");
            }

            readerRepo.save(reader);
            redirectAttributes.addFlashAttribute("success", "Thêm độc giả thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi thêm độc giả: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác thêm độc giả");
        }

        return "redirect:/readers";
    }

    @GetMapping("/readers/edit/{id}")
    public String showEditReaderForm(@PathVariable("id") String id,
                                     Model model, RedirectAttributes redirectAttributes) {
        try {
            Reader reader = readerRepo.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Độc giả không tồn tại"));

            model.addAttribute("reader", reader);
            model.addAttribute("readers", readerRepo.findAll());
            return "readers";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi hiển thị form sửa độc giả: " + e.getMessage());
            return "redirect:/readers";
        } finally {
            System.out.println("Hoàn tất thao tác hiển thị form sửa độc giả");
        }
    }

    @PostMapping("/readers/update")
    public String updateReader(@ModelAttribute("reader") Reader updatedReader,
                               RedirectAttributes redirectAttributes) {
        try {
            if (updatedReader.getId().isBlank() ||
                updatedReader.getName().isBlank() ||
                updatedReader.getCccd().isBlank()) {
                throw new IllegalArgumentException("Vui lòng điền đầy đủ mã độc giả, tên và CCCD");
            }

            if (!readerRepo.existsById(updatedReader.getId())) {
                throw new IllegalArgumentException("Độc giả không tồn tại");
            }

            readerRepo.save(updatedReader);
            redirectAttributes.addFlashAttribute("success", "Cập nhật độc giả thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi cập nhật độc giả: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác cập nhật độc giả");
        }

        return "redirect:/readers";
    }

    @GetMapping("/readers/delete/{id}")
    public String deleteReader(@PathVariable("id") String id,
                               RedirectAttributes redirectAttributes) {
        try {
            if (!readerRepo.existsById(id)) {
                throw new IllegalArgumentException("Độc giả không tồn tại");
            }

            readerRepo.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Xóa độc giả thành công");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi khi xóa độc giả: " + e.getMessage());
        } finally {
            System.out.println("Hoàn tất thao tác xóa độc giả");
        }

        return "redirect:/readers";
    }

    // Cho phép các controller khác gọi để lấy danh sách độc giả
    public List<Reader> getReaders() {
        return readerRepo.findAll();
    }
}
