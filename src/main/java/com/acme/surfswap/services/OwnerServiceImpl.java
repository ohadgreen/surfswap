package com.acme.surfswap.services;

import com.acme.surfswap.model.Owner;
import com.acme.surfswap.repositories.OwnerRepository;
import com.acme.surfswap.repositories.SurfboardRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final SurfboardRepository surfboardRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository, SurfboardRepository surfboardRepository) {
        this.ownerRepository = ownerRepository;
        this.surfboardRepository = surfboardRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerRepository.findAll().forEach(ownerSet::add);
        return ownerSet;
    }

    @Override
    public Owner findById(Long aLong) {
        return ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public void delete(Owner owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }
}
