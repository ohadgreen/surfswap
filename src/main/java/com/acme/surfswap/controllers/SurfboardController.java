package com.acme.surfswap.controllers;

import com.acme.surfswap.model.Surfboard;
import com.acme.surfswap.services.SurfboardServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/boards")
@CrossOrigin(origins = "http://localhost:3000")
public class SurfboardController {

    private final SurfboardServiceImpl surfboardService;

    public SurfboardController(SurfboardServiceImpl surfboardService) {
        this.surfboardService = surfboardService;
    }

    @GetMapping("/all")
    public Set<Surfboard> allBoards() {
        return surfboardService.findAll();
    }

    @GetMapping("/bystore/{storeid}")
    public Set<Surfboard> boardsByStore(@PathVariable long storeid) {
        log.debug("storeid: " + storeid );
        return surfboardService.findByStore(storeid);
    }

    @GetMapping("/byowner")
    public Set<Surfboard> boardsByOwner(@RequestParam("ownerId") Long ownerId) {
        log.debug("Owner id: " + ownerId );
        return surfboardService.findByOwner(ownerId);
    }

    @GetMapping("/byid")
    public Surfboard boardById(@RequestParam("boardId") Long boardId) {
        log.debug("board id: " + boardId );
        return surfboardService.findById(boardId);
    }

    @PostMapping("/new")
    public Long saveNewBoard(@Valid Surfboard surfboard) {
        log.debug("new board: " + surfboard.toString());
        Surfboard savedSurfboard = surfboardService.save(surfboard);
        return savedSurfboard.getId();
    }

    @DeleteMapping("/deleteById")
    public String deleteBoard(Long id) {
        surfboardService.deleteById(id);
        return "board deleted";
    }

    @GetMapping("/")
    public void boardsControllerTest() {
        log.debug("*** Surfboard controller");
    }
}
