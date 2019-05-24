package com.acme.surfswap.controllers;

import com.acme.surfswap.model.Store;
import com.acme.surfswap.model.Surfboard;
import com.acme.surfswap.services.SurfboardServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class SurfboardController {

    private final SurfboardServiceImpl surfboardService;

    public SurfboardController(SurfboardServiceImpl surfboardService) {
        this.surfboardService = surfboardService;
    }

    @GetMapping("/boards/all")
    public Set<Surfboard> allBoards() {
        return surfboardService.findAll();
    }

    @GetMapping("/boardsbystore/{storeId}")
    public Set<Surfboard> allBoardsByStore(Long storeId) {
        System.out.println("storeId = " + storeId);
        return surfboardService.findByStore(storeId);
    }
}
