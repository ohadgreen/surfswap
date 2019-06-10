package com.acme.surfswap.controllers;

import com.acme.surfswap.model.Surfboard;
import com.acme.surfswap.services.SurfboardServiceImpl;
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
//    private MockMvc mockMvc;

    @Mock
    SurfboardServiceImpl surfboardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        surfboardController = new SurfboardController(surfboardService);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(this.surfboardController).build();
    }

    @Test
    public void getAllSurfboardFromController() {
        Set<Surfboard> surfboardSet = new HashSet<>();
        surfboardSet.add(new Surfboard());

        when(surfboardService.findAll()).thenReturn(surfboardSet);
        assertEquals(1, surfboardController.allBoards().size());
        verify(surfboardService, times(1)).findAll();
    }

    @Test
    public void getSingleSurfboardById() {
        Surfboard surfboard = new Surfboard();

        when(surfboardService.findById(any())).thenReturn(surfboard);
        assertEquals(surfboard, surfboardController.boardById(1L));
    }

    @Test
    public void testMockMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(surfboardController).build();
        mockMvc.perform(get("/api/boards/all"))
                .andExpect(status().isOk());
    }
}