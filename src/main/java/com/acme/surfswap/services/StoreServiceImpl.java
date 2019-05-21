package com.acme.surfswap.services;

import com.acme.surfswap.model.Store;
import com.acme.surfswap.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class StoreServiceImpl implements StoreService {
    private StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store findByName(String name) {
        return storeRepository.findByName(name);
    }

    @Override
    public Set findAll() {
        Set<Store> storeSet = new HashSet<>();
        storeRepository.findAll().forEach(storeSet::add);
        return storeSet;
    }

    @Override
    public Store findById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }

    @Override
    public Store save(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public void delete(Store store) {
        storeRepository.delete(store);
    }

    @Override
    public void deleteById(Long id) {
        storeRepository.deleteById(id);
    }
}
