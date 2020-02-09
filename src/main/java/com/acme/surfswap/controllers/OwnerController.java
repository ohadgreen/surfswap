package com.acme.surfswap.controllers;


import com.acme.surfswap.model.Owner;
import com.acme.surfswap.model.Surfboard;
import com.acme.surfswap.services.OwnerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/owners")
public class OwnerController {
    private final OwnerServiceImpl ownerService;

    public OwnerController(OwnerServiceImpl ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/all")
    public ResponseEntity<Set<Owner>> allOwners() {
        Set<Owner> allOwners = ownerService.findAll();
        if (allOwners.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(allOwners, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> ownerById(@PathVariable Long id) {
        log.debug("owner id: " + id);
        Owner owner = ownerService.findById(id);
        if (owner != null) {
            return new ResponseEntity<>(owner, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/new")
    public ResponseEntity<Long> saveNewOwner(@RequestBody Owner owner) {
        log.debug("new owner: " + owner.toString());
        Owner savedOwner = ownerService.save(owner);
        if (savedOwner != null) {
            return new ResponseEntity<>(savedOwner.getId(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
