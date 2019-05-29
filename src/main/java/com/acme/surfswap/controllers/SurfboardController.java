package com.acme.surfswap.controllers;

import com.acme.surfswap.model.Store;
import com.acme.surfswap.model.Surfboard;
import com.acme.surfswap.services.SurfboardServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SurfboardController {

    private final SurfboardServiceImpl surfboardService;

    public SurfboardController(SurfboardServiceImpl surfboardService) {
        this.surfboardService = surfboardService;
    }

    @GetMapping("api/boards/all")
    public Set<Surfboard> allBoards() {
        return surfboardService.findAll();
    }

    @GetMapping("api/boards/bystore/{storeid}")
    public Set<Surfboard> boardsByStore(@PathVariable long storeid) {
        log.debug("storeid: " + storeid );
        return surfboardService.findByStore(storeid);
    }

    @GetMapping("api/boards/byowner")
    public Set<Surfboard> boardsByOwner(@RequestParam("ownerId") Long ownerId) {
        log.debug("Owner id: " + ownerId );
        return surfboardService.findByOwner(ownerId);
    }

    @GetMapping("api/boards/byid")
    public Surfboard boardById(@RequestParam("boardId") Long boardId) {
        log.debug("board id: " + boardId );
        return surfboardService.findById(boardId);
    }
}
