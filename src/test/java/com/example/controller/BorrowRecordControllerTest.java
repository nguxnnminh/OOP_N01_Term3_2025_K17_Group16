package com.example.controller;

import com.example.model.BorrowRecord;
import com.example.repository.BookRepository;
import com.example.repository.BorrowRecordRepository;
import com.example.repository.ReaderRepository;
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

public class BorrowRecordControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BorrowRecordRepository borrowRecordRepo;

    @Mock
    private BookRepository bookRepo;

    @Mock
    private ReaderRepository readerRepo;

    @InjectMocks
    private BorrowRecordController borrowRecordController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(borrowRecordController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void testShowBorrowRecords() throws Exception {
        List<BorrowRecord> records = new ArrayList<>();
        records.add(new BorrowRecord("1", "B1", "R1", "2024-01-01", ""));

        when(borrowRecordRepo.findAll()).thenReturn(records);

        mockMvc.perform(get("/borrow-records"))
                .andExpect(status().isOk())
                .andExpect(view().name("borrow-records"))
                .andExpect(model().attributeExists("records"))
                .andExpect(model().attributeExists("record"));
    }

    @Test
    public void testAddBorrowRecord_Valid() throws Exception {
        when(borrowRecordRepo.existsById("1")).thenReturn(false);
        when(bookRepo.existsById("B1")).thenReturn(true);
        when(readerRepo.existsById("R1")).thenReturn(true);
        when(borrowRecordRepo.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(post("/borrow-records/add")
                        .param("id", "1")
                        .param("bookId", "B1")
                        .param("readerId", "R1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attribute("success", "Thêm phiếu mượn thành công"));
    }

    @Test
    public void testAddBorrowRecord_BookNotExist() throws Exception {
        when(borrowRecordRepo.existsById("1")).thenReturn(false);
        when(bookRepo.existsById("B1")).thenReturn(false);

        mockMvc.perform(post("/borrow-records/add")
                        .param("id", "1")
                        .param("bookId", "B1")
                        .param("readerId", "R1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attributeExists("error"));
    }

    @Test
    public void testShowEditBorrowRecordForm_Valid() throws Exception {
        BorrowRecord record = new BorrowRecord("1", "B1", "R1", "2024-01-01", "");
        when(borrowRecordRepo.findById("1")).thenReturn(Optional.of(record));
        when(borrowRecordRepo.findAll()).thenReturn(List.of(record));

        mockMvc.perform(get("/borrow-records/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("borrow-records"))
                .andExpect(model().attributeExists("record"));
    }

    @Test
    public void testShowEditBorrowRecordForm_Invalid() throws Exception {
        when(borrowRecordRepo.findById("999")).thenReturn(Optional.empty());

        mockMvc.perform(get("/borrow-records/edit/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attributeExists("error"));
    }

    @Test
    public void testDeleteBorrowRecord_Valid() throws Exception {
        when(borrowRecordRepo.existsById("1")).thenReturn(true);

        mockMvc.perform(get("/borrow-records/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attribute("success", "Xóa phiếu mượn thành công"));
    }

    @Test
    public void testDeleteBorrowRecord_Invalid() throws Exception {
        when(borrowRecordRepo.existsById("999")).thenReturn(false);

        mockMvc.perform(get("/borrow-records/delete/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/borrow-records"))
                .andExpect(flash().attributeExists("error"));
    }
}
