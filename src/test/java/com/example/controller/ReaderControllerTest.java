package com.example.controller;

import com.example.model.Reader;
import com.example.model.BorrowRecord;
import com.example.repository.ReaderRepository;
import com.example.repository.BorrowRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReaderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReaderRepository readerRepo;

    @Mock
    private BorrowRecordRepository borrowRecordRepo;

    @InjectMocks
    private ReaderController readerController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Cấu hình Thymeleaf view resolver giả lập
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(readerController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testShowReaders_All() throws Exception {
        List<Reader> readers = new ArrayList<>();
        readers.add(new Reader("R1", "Nguyễn Văn A", "0123456789", "Hà Nội", "01234"));

        when(readerRepo.findAll()).thenReturn(readers);

        mockMvc.perform(get("/readers"))
                .andExpect(status().isOk())
                .andExpect(view().name("readers"))
                .andExpect(model().attributeExists("readers"))
                .andExpect(model().attributeExists("reader"));
    }

    @Test
    public void testAddReader_Valid() throws Exception {
        when(readerRepo.existsById("R1")).thenReturn(false);

        mockMvc.perform(post("/readers/add")
                        .param("id", "R1")
                        .param("name", "Nguyễn Văn A")
                        .param("cccd", "0123456789")
                        .param("address", "Hà Nội")
                        .param("phone", "01234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attribute("success", "Thêm độc giả thành công"));
    }

    @Test
    public void testAddReader_MissingField() throws Exception {
        mockMvc.perform(post("/readers/add")
                        .param("id", "")
                        .param("name", "Nguyễn Văn A")
                        .param("cccd", "0123456789"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attributeExists("error"));
    }

    @Test
    public void testShowEditReaderForm_Valid() throws Exception {
        Reader reader = new Reader("R1", "Nguyễn Văn A", "0123456789", "Hà Nội", "01234");

        when(readerRepo.findById("R1")).thenReturn(Optional.of(reader));
        when(readerRepo.findAll()).thenReturn(List.of(reader));

        mockMvc.perform(get("/readers/edit/R1"))
                .andExpect(status().isOk())
                .andExpect(view().name("readers"))
                .andExpect(model().attributeExists("reader"))
                .andExpect(model().attributeExists("readers"));
    }

    @Test
    public void testShowEditReaderForm_NotFound() throws Exception {
        when(readerRepo.findById("999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/readers/edit/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attributeExists("error"));
    }

    @Test
    public void testUpdateReader_Valid() throws Exception {
        when(readerRepo.existsById("R1")).thenReturn(true);

        mockMvc.perform(post("/readers/update")
                        .param("id", "R1")
                        .param("name", "Nguyễn Văn A")
                        .param("cccd", "0123456789")
                        .param("address", "Hà Nội")
                        .param("phone", "01234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attribute("success", "Cập nhật độc giả thành công"));
    }

    @Test
    public void testUpdateReader_NotExist() throws Exception {
        when(readerRepo.existsById("R1")).thenReturn(false);

        mockMvc.perform(post("/readers/update")
                        .param("id", "R1")
                        .param("name", "Nguyễn Văn A")
                        .param("cccd", "0123456789")
                        .param("address", "Hà Nội")
                        .param("phone", "01234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attributeExists("error"));
    }

    @Test
    public void testDeleteReader_Valid() throws Exception {
        when(readerRepo.existsById("R1")).thenReturn(true);
        when(borrowRecordRepo.findByReaderId("R1")).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/readers/delete/R1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attribute("success", "Xóa độc giả thành công"));
    }

    @Test
    public void testDeleteReader_NotExist() throws Exception {
        when(readerRepo.existsById("999")).thenReturn(false);

        mockMvc.perform(get("/readers/delete/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attributeExists("error"));
    }

    @Test
    public void testDeleteReader_HasBorrowRecords() throws Exception {
        when(readerRepo.existsById("R1")).thenReturn(true);
        when(borrowRecordRepo.findByReaderId("R1")).thenReturn(List.of(new BorrowRecord()));

        mockMvc.perform(get("/readers/delete/R1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/readers"))
                .andExpect(flash().attributeExists("error"));
    }
}
