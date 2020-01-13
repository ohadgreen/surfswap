package com.acme.surfswap.controllers;

import com.acme.surfswap.model.Surfboard;
import com.acme.surfswap.services.SurfboardServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Set<Surfboard>> allBoards() {
        return new ResponseEntity<>(surfboardService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/bystore/{storeid}")
    public ResponseEntity<Set<Surfboard>> boardsByStore(@PathVariable long storeid) {
        log.debug("storeid: " + storeid );
        return new ResponseEntity<>(surfboardService.findByStore(storeid), HttpStatus.OK);
    }

    @GetMapping("/byowner")
    public ResponseEntity<Set<Surfboard>> boardsByOwner(@RequestParam("ownerId") Long ownerId) {
        log.debug("Owner id: " + ownerId );
        return new ResponseEntity<>(surfboardService.findByOwner(ownerId), HttpStatus.OK);
    }

    @GetMapping("/byid")
    public ResponseEntity<Surfboard> boardById(@RequestParam("boardId") Long boardId) {
        log.debug("board id: " + boardId );
        return new ResponseEntity<>(surfboardService.findById(boardId), HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<Long> saveNewBoard(@Valid Surfboard surfboard) {
        log.debug("new board: " + surfboard.toString());
        Surfboard savedSurfboard = surfboardService.save(surfboard);
        if (savedSurfboard != null) {
            return new ResponseEntity<>(savedSurfboard.getId(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<HttpStatus> deleteBoard(Long id) {
        surfboardService.deleteById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/")
    public void boardsControllerTest() {
        log.debug("*** Surfboard controller");
    }
}
