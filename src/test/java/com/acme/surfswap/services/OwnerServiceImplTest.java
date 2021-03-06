package com.acme.surfswap.services;

import com.acme.surfswap.model.Owner;
import com.acme.surfswap.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerServiceImplTest {

    private static final String LAST_NAME = "Slater";
    private Set<Owner> ownerSet = new HashSet<>();
    private Owner slater = new Owner();
    @Mock
    OwnerRepository ownerRepository;
    @InjectMocks
    OwnerServiceImpl service;

    @BeforeEach
    void setUp() {
        ownerSet.add(new Owner("John", "Florence", "4567"));
        ownerSet.add(new Owner("TJ", "Slater", "9999"));

        Owner ks = new Owner("Kelly", "Slater", "1234");
        ownerSet.add(ks);
    }

    @Test
    void findByLastName() {
        slater.setLastName(LAST_NAME);
        when(ownerRepository.findByLastName((any()))).thenReturn(slater);
        Owner returnedOwner = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, returnedOwner.getLastName());
    }

    @Test
    void findById() {
        slater.setId(1l);
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

    @Test
    public void testFindAll() {
        when(ownerRepository.findAll()).thenReturn(ownerSet);
        Set<Owner> ownerSet = service.findAll();
        System.out.println(ownerSet);
        assertEquals(3, ownerSet.size());
    }

    @Test
    public void testFindAllByLastNameLike() {
        when(ownerRepository.findAll()).thenReturn(ownerSet);
        Set<Owner> allByLastNameLike = service.findAllByLastNameLike(LAST_NAME);
        System.out.println(allByLastNameLike);
        assertEquals(2, allByLastNameLike.size());
    }

    @Test
    void ownerSetAddTest() {
        Set<Owner> myOwnerTestSet = new HashSet<>();
        myOwnerTestSet.add(new Owner("John", "Florence", "4567"));
        myOwnerTestSet.add(new Owner("KK", "TT", "123543"));

        System.out.println(myOwnerTestSet.size());
    }

}
