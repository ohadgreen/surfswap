package com.acme.surfswap.controllers;


import com.acme.surfswap.model.Owner;
import com.acme.surfswap.services.OwnerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.Set;

@Slf4j
@RestController
public class OwnerController {
    private final OwnerServiceImpl ownerService;

    public OwnerController(OwnerServiceImpl ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("api/owners/all")
    public ResponseEntity<Set<Owner>> allOwners() {
        Set<Owner> allOwners = ownerService.findAll();
        if (allOwners.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(allOwners, HttpStatus.OK);
    }

    @GetMapping("api/owners/{id}")
    public ResponseEntity<Owner> ownerById(@PathVariable Long id) {
        log.debug("owner id: " + id);
        Owner owner = ownerService.findById(id);
        if (owner != null) {
            return new ResponseEntity<>(owner, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
