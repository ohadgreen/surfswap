package com.acme.surfswap.services;

import com.acme.surfswap.model.Owner;
import com.acme.surfswap.model.Surfboard;
import com.acme.surfswap.repositories.SurfboardRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.StreamSupport;

@Service
public class SurfboardServiceImpl implements SurfboardService {
    private final SurfboardRepository surfboardRepository;
    private final OwnerService ownerService;

    public SurfboardServiceImpl(SurfboardRepository surfboardRepository, OwnerService ownerService) {
        this.surfboardRepository = surfboardRepository;
        this.ownerService = ownerService;
    }

    @Override
    public Set<Surfboard> findAll() {
        Set<Surfboard> surfboardSet = new HashSet<>();
        surfboardRepository.findAll().forEach(surfboardSet::add);
        return surfboardSet;
    }

    public Set<Surfboard> findByStore(Long storeId) {
        Set<Surfboard> surfboardSet = new HashSet<>();
        Iterable<Surfboard> allSurfboards = surfboardRepository.findAll();
        StreamSupport.stream(allSurfboards.spliterator(), false)
                .filter(surfboard -> surfboard.getStore().getId() == storeId)
                .forEach(surfboard -> surfboardSet.add(surfboard));
        return surfboardSet;
    }

    @Override
    public Surfboard findById(Long aLong) {
        return surfboardRepository.findById(aLong).orElse(null);
    }

    @Override
    public Surfboard save(Surfboard surfboard) {
        return surfboardRepository.save(surfboard);
    }

    @Override
    public void delete(Surfboard surfboard) {
        surfboardRepository.delete(surfboard);
    }

    @Override
    public void deleteById(Long aLong) {
        surfboardRepository.deleteById(aLong);
    }

    public Set<Surfboard> findByOwner(Long ownerId) {
        Owner boardOwner = ownerService.findById(ownerId);
        if (boardOwner != null) {
            return boardOwner.getSurfboards();
        }
        return null;
    }
}
