package com.acme.surfswap.controllers;

import com.acme.surfswap.enums.SurfboardType;
import com.acme.surfswap.model.Surfboard;
import com.acme.surfswap.services.SurfboardServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SurfboardControllerTest {

    SurfboardController surfboardController;
    private MockMvc mockMvc;
    private Set<Surfboard> surfboardSet;

    @Mock
    SurfboardServiceImpl surfboardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        surfboardController = new SurfboardController(surfboardService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.surfboardController).build();
        this.surfboardSet = new HashSet<>();

        Surfboard testFunboard = new Surfboard();
        testFunboard.setId(1L);
        testFunboard.setBrand("BG");
        testFunboard.setSurfboardType(SurfboardType.FUN_BOARD);

        Surfboard testShortboard = new Surfboard();
        testShortboard.setId(2L);
        testShortboard.setSurfboardType(SurfboardType.SHORT_BOARD);
        this.surfboardSet.add(testFunboard);
        this.surfboardSet.add(testShortboard);
        when(surfboardService.findAll()).thenReturn(surfboardSet);
        when(surfboardService.findById(1L)).thenReturn(testFunboard);
    }

    @Test
    public void getAllSurfboardFromController() {
        assertEquals(2, surfboardController.allBoards().size());
        verify(surfboardService, times(1)).findAll();
    }

    @Test
    public void getSingleSurfboardById() {
        assertEquals("BG", surfboardController.boardById(1L).getBrand());
    }

    @Test
    public void testMockMvc() throws Exception {
        mockMvc.perform(get("/api/boards/all"))
                .andExpect(status().isOk());
    }
}