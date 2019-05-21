package com.acme.surfswap.services;

import com.acme.surfswap.model.Surfboard;
import com.acme.surfswap.repositories.SurfboardRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SurfboardServiceImpl implements SurfboardService {
    private final SurfboardRepository surfboardRepository;

    public SurfboardServiceImpl(SurfboardRepository surfboardRepository) {
        this.surfboardRepository = surfboardRepository;
    }

    @Override
    public Set<Surfboard> findAll() {
        Set<Surfboard> surfboardSet = new HashSet<>();
        surfboardRepository.findAll().forEach(surfboardSet::add);
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
}
