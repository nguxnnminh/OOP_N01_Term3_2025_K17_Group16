package com.example.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReaderControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ReaderController readerController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(readerController).build();
    }

    @Test
    public void testShowReaders() throws Exception {
        mockMvc.perform(get("/readers"))
                .andExpect(status().isOk())
                .andExpect(view().name("readers"))
                .andExpect(model().attributeExists("readers"))
                .andExpect(model().attributeExists("reader"));
    }

    @Test
    public void testAddReader_Valid() throws Exception {
        mockMvc.perform(post("/readers/add")
                .param("id", "R4")
                .param("name", "Phạm Văn D")
                .param("cccd", "789123456789")
                .param("phone", "0934567890")
                .param("birthDate", "1998-04-04"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attribute("success", "Thêm độc giả thành công"));
    }

    @Test
    public void testAddReader_InvalidId() throws Exception {
        mockMvc.perform(post("/readers/add")
                .param("id", "")
                .param("name", "Phạm Văn D")
                .param("cccd", "789123456789")
                .param("phone", "0934567890")
                .param("birthDate", "1998-04-04"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attribute("error", "Vui lòng điền đầy đủ mã độc giả, tên và CCCD"));
    }

    @Test
    public void testShowEditReaderForm_ValidId() throws Exception {
        mockMvc.perform(get("/readers/edit/R1"))
                .andExpect(status().isOk())
                .andExpect(view().name("readers"))
                .andExpect(model().attributeExists("reader"))
                .andExpect(model().attributeExists("readers"));
    }

    @Test
    public void testShowEditReaderForm_InvalidId() throws Exception {
        mockMvc.perform(get("/readers/edit/R999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attribute("error", "Độc giả không tồn tại"));
    }

    @Test
    public void testUpdateReader_Valid() throws Exception {
        mockMvc.perform(post("/readers/update")
                .param("id", "R1")
                .param("name", "Nguyễn Văn A Cập nhật")
                .param("cccd", "123456789012")
                .param("phone", "0901234567")
                .param("birthDate", "1990-01-01"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attribute("success", "Cập nhật độc giả thành công"));
    }

    @Test
    public void testUpdateReader_InvalidId() throws Exception {
        mockMvc.perform(post("/readers/update")
                .param("id", "R999")
                .param("name", "Nguyễn Văn A Cập nhật")
                .param("cccd", "123456789012")
                .param("phone", "0901234567")
                .param("birthDate", "1990-01-01"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attribute("error", "Độc giả không tồn tại"));
    }

    @Test
    public void testDeleteReader_ValidId() throws Exception {
        mockMvc.perform(get("/readers/delete/R1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attribute("success", "Xóa độc giả thành công"));
    }

    @Test
    public void testDeleteReader_InvalidId() throws Exception {
        mockMvc.perform(get("/readers/delete/R999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attribute("error", "Độc giả không tồn tại"));
    }
}