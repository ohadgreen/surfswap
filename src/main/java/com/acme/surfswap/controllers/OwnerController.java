package com.acme.surfswap.controllers;


import com.acme.surfswap.model.Owner;
import com.acme.surfswap.services.OwnerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RestController
public class OwnerController {
    private final OwnerServiceImpl ownerService;

    public OwnerController(OwnerServiceImpl ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("api/owners/all")
    public Set<Owner> allOwners() {
        return ownerService.findAll();
    }

    @GetMapping("api/owners/{id}")
    public Owner ownerById(@PathVariable Long id) {
        log.debug("owner id: " + id);
        return ownerService.findById(id);
    }
}
