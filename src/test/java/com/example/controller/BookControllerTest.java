package com.example.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testShowBooks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attributeExists("book"));
    }

    @Test
    public void testAddBook_Valid() throws Exception {
        mockMvc.perform(post("/books/add")
                .param("id", "B4")
                .param("title", "Học Spring Boot")
                .param("author", "Phạm Văn D"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attribute("success", "Thêm sách thành công"));
    }

    @Test
    public void testAddBook_InvalidId() throws Exception {
        mockMvc.perform(post("/books/add")
                .param("id", "")
                .param("title", "Học Spring Boot")
                .param("author", "Phạm Văn D"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attribute("error", "Vui lòng điền đầy đủ thông tin sách"));
    }

    @Test
    public void testShowEditBookForm_ValidId() throws Exception {
        mockMvc.perform(get("/books/edit/B1"))
                .andExpect(status().isOk())
                .andExpect(view().name("books"))
                .andExpect(model().attributeExists("book"))
                .andExpect(model().attributeExists("books"));
    }

    @Test
    public void testShowEditBookForm_InvalidId() throws Exception {
        mockMvc.perform(get("/books/edit/B999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attribute("error", "Sách không tồn tại"));
    }

    @Test
    public void testUpdateBook_Valid() throws Exception {
        mockMvc.perform(post("/books/update")
                .param("id", "B1")
                .param("title", "Lập trình Java Nâng cao")
                .param("author", "Nguyễn Văn A"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attribute("success", "Cập nhật sách thành công"));
    }

    @Test
    public void testUpdateBook_InvalidId() throws Exception {
        mockMvc.perform(post("/books/update")
                .param("id", "B999")
                .param("title", "Lập trình Java Nâng cao")
                .param("author", "Nguyễn Văn A"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attribute("error", "Sách không tồn tại"));
    }

    @Test
    public void testDeleteBook_ValidId() throws Exception {
        mockMvc.perform(get("/books/delete/B1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attribute("success", "Xóa sách thành công"));
    }

    @Test
    public void testDeleteBook_InvalidId() throws Exception {
        mockMvc.perform(get("/books/delete/B999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/books"))
                .andExpect(flash().attribute("error", "Sách không tồn tại"));
    }
}