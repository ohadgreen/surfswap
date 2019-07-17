package com.acme.surfswap.controllers;

import com.acme.surfswap.model.Owner;
import com.acme.surfswap.services.OwnerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerServiceImpl ownerService;

    @InjectMocks
    OwnerController ownerController;

    private MockMvc mockMvc;
    private Set<Owner> ownerSet = new HashSet<>();

    @BeforeEach
    void setUp() {
        Owner owner1 = new Owner("John", "Florence", "12345678");
        Owner owner2 = new Owner("Kelly", "Slater", "12345678");
        ownerSet.add(owner1);
        ownerSet.add(owner2);

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void getAllOwnersOk() throws Exception {
        when(ownerService.findAll()).thenReturn(ownerSet);

        mockMvc.perform(get("/api/owners/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(ownerSet.size()));
    }


}
