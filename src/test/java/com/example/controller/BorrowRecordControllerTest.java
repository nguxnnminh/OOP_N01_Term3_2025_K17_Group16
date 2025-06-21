package com.example.controller;

import com.example.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BorrowRecordControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private BorrowRecordController borrowRecordController;

    @Mock
    private BookController bookController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(borrowRecordController).build();

        // Mock danh sách sách
        when(bookController.getBooks()).thenReturn(Arrays.asList(
                new Book("B1", "Lập trình Java", "Nguyễn Văn A"),
                new Book("B2", "Cấu trúc dữ liệu", "Trần Thị B"),
                new Book("B3", "Thuật toán", "Lê Văn C")
        ));
    }

    @Test
    public void testShowBorrowRecords() throws Exception {
        mockMvc.perform(get("/borrow-records"))
                .andExpect(status().isOk())
                .andExpect(view().name("borrow-records"))
                .andExpect(model().attributeExists("records"))
                .andExpect(model().attributeExists("record"));
    }

    @Test
    public void testAddBorrowRecord_Valid() throws Exception {
        mockMvc.perform(post("/borrow-records/add")
                .param("id", "BR4")
                .param("bookId", "B1")
                .param("readerId", "R4")
                .param("borrowDate", "2025-06-04")
                .param("returnDate", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attribute("success", "Thêm phiếu mượn thành công"));
    }

    @Test
    public void testAddBorrowRecord_InvalidId() throws Exception {
        mockMvc.perform(post("/borrow-records/add")
                .param("id", "")
                .param("bookId", "B1")
                .param("readerId", "R4")
                .param("borrowDate", "2025-06-04")
                .param("returnDate", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attribute("error", "Vui lòng điền đầy đủ ID phiếu mượn, mã sách và mã độc giả"));
    }

    @Test
    public void testAddBorrowRecord_BookNotExist() throws Exception {
        mockMvc.perform(post("/borrow-records/add")
                .param("id", "BR4")
                .param("bookId", "B999")
                .param("readerId", "R4")
                .param("borrowDate", "2025-06-04")
                .param("returnDate", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attribute("error", "Sách không tồn tại trong thư viện"));
    }

    @Test
    public void testAddBorrowRecord_BookBorrowed() throws Exception {
        // Giả lập thêm một phiếu mượn với sách B2 chưa trả
        mockMvc.perform(post("/borrow-records/add")
                .param("id", "BR4")
                .param("bookId", "B2")
                .param("readerId", "R4")
                .param("borrowDate", "2025-06-04")
                .param("returnDate", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attribute("success", "Thêm phiếu mượn thành công"));

        // Thử thêm phiếu mượn khác với cùng sách B2
        mockMvc.perform(post("/borrow-records/add")
                .param("id", "BR5")
                .param("bookId", "B2")
                .param("readerId", "R5")
                .param("borrowDate", "2025-06-05")
                .param("returnDate", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attribute("error", "Sách hiện đang được mượn"));
    }

    @Test
    public void testShowEditBorrowRecordForm_ValidId() throws Exception {
        mockMvc.perform(get("/borrow-records/edit/BR1"))
                .andExpect(status().isOk())
                .andExpect(view().name("borrow-records"))
                .andExpect(model().attributeExists("record"))
                .andExpect(model().attributeExists("records"));
    }

    @Test
    public void testShowEditBorrowRecordForm_InvalidId() throws Exception {
        mockMvc.perform(get("/borrow-records/edit/BR999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attribute("error", "Phiếu mượn không tồn tại"));
    }

    @Test
    public void testUpdateBorrowRecord_Valid() throws Exception {
        mockMvc.perform(post("/borrow-records/update")
                .param("id", "BR1")
                .param("bookId", "B1")
                .param("readerId", "R1")
                .param("borrowDate", "2025-06-01")
                .param("returnDate", "2025-06-16"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attribute("success", "Cập nhật phiếu mượn thành công"));
    }

    @Test
    public void testUpdateBorrowRecord_InvalidId() throws Exception {
        mockMvc.perform(post("/borrow-records/update")
                .param("id", "BR999")
                .param("bookId", "B1")
                .param("readerId", "R1")
                .param("borrowDate", "2025-06-01")
                .param("returnDate", "2025-06-16"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attribute("error", "Phiếu mượn không tồn tại"));
    }

    @Test
    public void testDeleteBorrowRecord_ValidId() throws Exception {
        mockMvc.perform(get("/borrow-records/delete/BR1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attribute("success", "Xóa phiếu mượn thành công"));
    }

    @Test
    public void testDeleteBorrowRecord_InvalidId() throws Exception {
        mockMvc.perform(get("/borrow-records/delete/BR999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attribute("error", "Phiếu mượn không tồn tại"));
    }
}