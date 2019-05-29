package com.acme.surfswap.controllers;

import com.acme.surfswap.model.Surfboard;
import com.acme.surfswap.services.SurfboardServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class SurfboardControllerTest {

    SurfboardController surfboardController;

    @Mock
    SurfboardServiceImpl surfboardService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        surfboardController = new SurfboardController(surfboardService);
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
}