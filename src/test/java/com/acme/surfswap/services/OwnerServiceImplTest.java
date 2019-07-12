package com.acme.surfswap.services;

import com.acme.surfswap.model.Owner;
import com.acme.surfswap.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {

    public static final String LAST_NAME = "Slater";
    Owner slater = new Owner();
    @Mock
    OwnerRepository ownerRepository;
    @InjectMocks
    OwnerServiceImpl service;

    @BeforeEach
    void setUp() {
        slater.setLastName(LAST_NAME);
        slater.setId(1L);
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName((any()))).thenReturn(slater);
        Owner returnedOwner = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, returnedOwner.getLastName());
    }

    @Test
    void findById() {
        when(ownerRepository.findById((anyLong()))).thenReturn(of(slater));
        Owner returnedOwner = service.findById(1l);

        assertNotNull(returnedOwner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById((anyLong()))).thenReturn(Optional.empty());
        Owner returnedOwner = service.findById(99l);

        assertNull(returnedOwner);
    }
}