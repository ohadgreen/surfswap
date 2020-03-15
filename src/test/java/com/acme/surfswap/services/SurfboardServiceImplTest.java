package com.acme.surfswap.services;

import com.acme.surfswap.model.Surfboard;
import com.acme.surfswap.repositories.SurfboardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SurfboardServiceImplTest {

    SurfboardServiceImpl surfboardService;

    @Mock
    SurfboardRepository surfboardRepository;
    OwnerServiceImpl ownerService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        surfboardService = new SurfboardServiceImpl(surfboardRepository);
    }
    @Test
    public void getSurfboardSet() {
        Set<Surfboard> surfboardSet = new HashSet<>();
        Surfboard surfboard = new Surfboard();
        surfboardSet.add(surfboard);

        when(surfboardRepository.findAll()).thenReturn(surfboardSet);
        Set<Surfboard> allSurfboardSet = surfboardService.findAll();
        assertEquals(1, allSurfboardSet.size());
        // verifies the repository is being called exactly once
        verify(surfboardRepository, times(1)).findAll();
    }
}